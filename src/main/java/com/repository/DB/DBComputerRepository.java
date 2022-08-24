package com.repository.DB;

import com.annotations.Singleton;
import com.config.JDBCConfig;
import com.model.Product;
import com.model.computer.Computer;
import com.model.computer.ManufacturerComputer;
import com.model.phone.Manufacturer;
import com.model.phone.Phone;
import com.repository.CrudRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Stream;


@Singleton
public class DBComputerRepository implements CrudRepository<Computer> {

    private static final Connection CONNECTION = JDBCConfig.getConnection();

    private static DBComputerRepository instance;
    public static CrudRepository<Computer> getInstance() {
        if (instance == null){
            instance = new DBComputerRepository();
        }
        return instance;
    }
    @Override
    public Product save(Computer product) {
        String sql = "INSERT INTO \"public\".\"Computer\" (id , model, manufacturer, price, count, title) VALUES (?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            setObjectsFields(statement, product);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @SneakyThrows
    private void setObjectsFields(final PreparedStatement statement, final Computer computer) {
        statement.setString(1, computer.getId());
        statement.setString(2, computer.getModel());
        statement.setString(3, computer.getManufacturer().name());
        statement.setDouble(4, computer.getPrice());
        statement.setInt(5, computer.getCount());
        statement.setString(6, computer.getTitle());
    }

    @Override
    public void saveAll(List<Computer> product) {
        String sql = "INSERT INTO \"public\".\"Computer\" (id , model, manufacturer, price, count, title) VALUES (?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            CONNECTION.setAutoCommit(false);
            for (Computer computer:product) {
                setObjectsFields(statement, computer);
                statement.addBatch();
            }
            statement.executeBatch();
            CONNECTION.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(Computer product) {
        String sql  = "UPDATE \"public\".\"Computer\" SET model = ?,  manufacturer = ?, price = ?, count = ?, title = ? WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(5, product.getTitle());
            statement.setString(1, product.getModel());
            statement.setString(2, product.getManufacturer().name());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getCount());
            statement.setString(6, product.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM \"public\".\"Computer\" WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1,id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Computer> getAll() {
        final List<Computer> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {

            final ResultSet resultSet = statement.executeQuery("SELECT * FROM \"public\".\"Computer\"");
            while (resultSet.next()) {
                result.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @SneakyThrows
    private Computer setFieldsToObject(final ResultSet resultSet) {
        Computer computer = new Computer(resultSet.getString("title"),
                resultSet.getInt("count"),
                resultSet.getDouble("price"),
                resultSet.getString("model"),
                ManufacturerComputer.valueOf(resultSet.getString("manufacturer")),
                resultSet.getString("invoiceId"));
        computer.setId(resultSet.getString("id"));
        return computer;
    }

    @Override
    public Optional<Computer> findById(String id) {
        String sql = "SELECT * FROM \"public\".\"Computer\" WHERE id = ?";
        Optional<Computer> computer = Optional.empty();
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                computer = Optional.of(setFieldsToObject(resultSet));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return computer;
    }

    public List<Computer> findByInvioceId(String invioceId) {
        String sql = "SELECT * FROM \"public\".\"Computer\" WHERE \"invoiceID\" = ?";
        Optional<Computer> computer = Optional.empty();
        List<Computer> result = new ArrayList<>();
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

    public List<Computer> findWithInvoiceIdNull() {
        String sql = "SELECT * FROM \"public\".\"Computer\" WHERE \"invoiceID\" = NULL";
        Optional<Computer> computer = Optional.empty();
        List<Computer> result = new ArrayList<>();
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
