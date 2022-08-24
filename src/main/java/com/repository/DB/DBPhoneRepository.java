package com.repository.DB;

import com.annotations.Singleton;
import com.config.JDBCConfig;
import com.model.Product;
import com.model.computer.Computer;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.CrudRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class DBPhoneRepository implements CrudRepository<Phone> {

    private static final Connection CONNECTION = JDBCConfig.getConnection();

    private static DBPhoneRepository instance;
    public static CrudRepository<Phone> getInstance() {
        if (instance == null){
            instance = new DBPhoneRepository();
        }
        return instance;
    }

    @Override
    public Product save(Phone product) {
        String sql = "INSERT INTO \"public\".\"Phone\" (id , model, manufacturer, price, count, title) VALUES (?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            setObjectsFields(statement, product);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @SneakyThrows
    private void setObjectsFields(final PreparedStatement statement, final Phone phone) {
        statement.setString(1, phone.getId());
        statement.setString(2, phone.getModel());
        statement.setString(3, phone.getManufacturer().name());
        statement.setDouble(4, phone.getPrice());
        statement.setInt(5, phone.getCount());
        statement.setString(6, phone.getTitle());
    }

    @Override
    public void saveAll(List<Phone> product) {
        String sql = "INSERT INTO \"public\".\"Phone\" (id , model, manufacturer, price, count, title) VALUES (?, ?, ?, ?, ?, ?)";
        try(final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            CONNECTION.setAutoCommit(false);
            for (Phone phone:product ) {
                setObjectsFields(statement, phone);
                statement.addBatch();
            }
            statement.executeBatch();
            CONNECTION.commit();
            CONNECTION.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Phone product) {
        String sql  = "UPDATE \"public\".\"Phone\" SET model = ?,  manufacturer = ?, price = ?, count = ?, title = ? WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(5, product.getTitle());
            statement.setString(1, product.getModel());
            statement.setString(2, product.getManufacturer().name());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getCount());
            statement.setString(6, product.getId());
            statement.executeUpdate();
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM \"public\".\"Phone\" WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1,id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Phone> getAll() {
        final List<Phone> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {

            final ResultSet resultSet = statement.executeQuery("SELECT * FROM \"public\".\"Phone\"");
            while (resultSet.next()) {
                result.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @SneakyThrows
    private Phone setFieldsToObject(final ResultSet resultSet) {
        Phone phone = new Phone(resultSet.getString("title"),
                resultSet.getInt("count"),
                resultSet.getDouble("price"),
                resultSet.getString("model"),
                EnumUtils.getEnum(Manufacturer.class, resultSet.getString("manufacturer")),
                resultSet.getString("invoiceID"));
        phone.setId(resultSet.getString("id"));
        return phone;
    }

    @Override
    public Optional<Phone> findById(String id) {
        String sql = "SELECT * FROM \"public\".\"Phone\" WHERE id = ?";
        Optional<Phone> phone = Optional.empty();
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)){
        statement.setString(1, id);
        final ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            phone = Optional.of(setFieldsToObject(resultSet));
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return phone;
    }

    public List<Phone> findByInvioceId(String invioceId) {
        String sql = "SELECT * FROM \"public\".\"Phone\" WHERE \"invoiceID\" = ?";
        Optional<Phone> phone = Optional.empty();
        List<Phone> result = new ArrayList<>();
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1, invioceId);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(setFieldsToObject(resultSet));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Phone> findWithInvoiceIdNull() {
        String sql = "SELECT * FROM \"public\".\"Phone\" WHERE \"invoiceID\" = NULL";
        Optional<Phone> phone = Optional.empty();
        List<Phone> result = new ArrayList<>();
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(setFieldsToObject(resultSet));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }
}
