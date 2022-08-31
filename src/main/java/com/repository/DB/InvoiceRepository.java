package com.repository.DB;
import com.annotations.Singleton;
import com.config.JDBCConfig;
import com.model.Invoice;
import com.model.Product;
import com.model.ProductType;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalTime;
import java.util.*;

@Singleton
public class InvoiceRepository implements com.repository.InvoiceRepository {

    private static final Connection CONNECTION = JDBCConfig.getConnection();

    private static InvoiceRepository instance;

    public static InvoiceRepository getInstance() {
        if (instance == null) {
            instance = new InvoiceRepository();
        }
        return instance;
    }

    @Override
    public Invoice save(Invoice invoice) {
        String sqlForInsert = "INSERT INTO \"public\".\"Invoice\" (id, sum, time) VALUES (?, ?, ?)";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sqlForInsert)){
            CONNECTION.setAutoCommit(false);
            statement.setString(1, invoice.getId());
            statement.setDouble(2, invoice.getSum());
            statement.setTime(3, Time.valueOf(invoice.getTime().toLocalTime()));
            statement.execute();
            List<Product> products = invoice.getProducts();
            for (Product product:products) {
                if (product.getType().equals(ProductType.PHONE)){
                    setInvoiceIdToPhone(invoice, product);
                }else if (product.getType().equals(ProductType.COMPUTER)){
                    setInvoiceIdToComputer(invoice, product);
                }else if (product.getType().equals(ProductType.TELEVISION)){
                    setInvioceIdToTelevision(invoice,product);
                }
            }
            CONNECTION.commit();
            CONNECTION.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }


    private void setInvioceIdToTelevision(Invoice invoice, Product product) {
        String sql = "UPDATE \"public\".\"Phone\" SET \"invoiceID\" = ? WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1, invoice.getId());
            statement.setString(2, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setInvoiceIdToComputer(Invoice invoice, Product product) {
        String sql = "UPDATE \"public\".\"Computer\" SET \"invoiceID\" = ? WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1, invoice.getId());
            statement.setString(2, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setInvoiceIdToPhone(Invoice invoice, Product product) {
        String sql = "UPDATE \"public\".\"Phone\" SET \"invoiceID\" = ? WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1, invoice.getId());
            statement.setString(2, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Invoice> findInvoicesWithSumHigher(Double sum) {
        String sql = "SELECT * FROM \"public\".\"Invoice\" WHERE sum > ?";
        List<Invoice> invoices = new ArrayList<>();
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setDouble(1, sum);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                invoices.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    @SneakyThrows
    private Invoice setFieldsToObject(final ResultSet resultSet) {
        Invoice invoice= new Invoice();
        invoice.setId(resultSet.getString("id"));
        invoice.setSum(resultSet.getDouble("sum"));
        invoice.setTime(resultSet.getDate("time").toLocalDate().atTime(LocalTime.now()));
        invoice.setId(resultSet.getString("id"));
        return invoice;
    }

    @Override
    public Long countOfInvoices() {
        String sql = "SELECT COUNT(*) FROM \"public\".\"Invoice\"";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public void update(Invoice invoice) {
        String sql = "UPDATE \"public\".\"Invoice\" SET time = ? WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setTime(1, Time.valueOf(invoice.getTime().toLocalTime()));
            statement.setString(2, invoice.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> groupInvoicesBySum() {
        List<String> result = new ArrayList<>();
        String sql = "SELECT sum, count(id) AS countOfInvioce  FROM \"public\".\"Invoice\" GROUP BY sum";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add("count = " + resultSet.getInt(2) + " Sum = " + resultSet.getDouble(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}