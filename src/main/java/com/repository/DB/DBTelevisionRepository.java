package com.repository.DB;

import com.annotations.Singleton;
import com.config.JDBCConfig;
import com.model.Product;
import com.model.phone.Phone;
import com.model.television.ManufacturerTelevision;
import com.model.television.Television;
import com.repository.CrudRepository;
import lombok.SneakyThrows;
import org.apache.commons.lang3.EnumUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class DBTelevisionRepository implements CrudRepository<Television> {
    private static final Connection CONNECTION = JDBCConfig.getConnection();

    private static DBTelevisionRepository instance;
    public static CrudRepository<Television> getInstance() {
        if (instance == null){
            instance = new DBTelevisionRepository();
        }
        return instance;
    }


    @Override
    public Product save(Television product) {
        String sql = "INSERT INTO \"public\".\"Television\" (id , model, manufacturer, price, count, title, diagonal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            setObjectsFields(statement, product);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @SneakyThrows
    private void setObjectsFields(final PreparedStatement statement, final Television television) {
        statement.setString(1, television.getId());
        statement.setString(2, television.getModel());
        statement.setString(3, television.getManufacturer().name());
        statement.setDouble(4, television.getPrice());
        statement.setInt(5, television.getCount());
        statement.setString(6, television.getTitle());
        statement.setInt(7, television.getDiagonal());
    }

    @Override
    public void saveAll(List<Television> product) {
        String sql = "INSERT INTO \"public\".\"Television\" (id , model, manufacturer, price, count, title, diagonal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            CONNECTION.setAutoCommit(false);
            for (Television television:product) {
                setObjectsFields(statement, television);
                statement.addBatch();
            }
            statement.executeBatch();
            CONNECTION.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Television product) {
        String sql  = "UPDATE \"public\".\"Television\" SET model = ?,  manufacturer = ?, price = ?, count = ?, title = ?, diagonal = ? WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(5, product.getTitle());
            statement.setString(1, product.getModel());
            statement.setString(2, product.getManufacturer().name());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getCount());
            statement.setString(7, product.getId());
            statement.setInt(6, product.getDiagonal());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM \"public\".\"Television\" WHERE id = ?";
        try (final PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1,id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Television> getAll() {
        final List<Television> result = new ArrayList<>();
        try (final Statement statement = CONNECTION.createStatement()) {

            final ResultSet resultSet = statement.executeQuery("SELECT * FROM \"public\".\"Television\"");
            while (resultSet.next()) {
                result.add(setFieldsToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @SneakyThrows
    private Television setFieldsToObject(final ResultSet resultSet) {
        Television television = new Television(resultSet.getString("title"),
                resultSet.getInt("count"),
                resultSet.getDouble("price"),
                resultSet.getString("model"),
                EnumUtils.getEnum(ManufacturerTelevision.class, resultSet.getString("manufacturer")),
                resultSet.getInt("diagonal"),
                resultSet.getString("invoiceID"));
        television.setId(resultSet.getString("id"));
        return television;
    }

    @Override
    public Optional<Television> findById(String id) {
        String sql = "SELECT * FROM \"public\".\"Television\" WHERE id = ?";
        Optional<Television> television = Optional.empty();
        try (PreparedStatement statement = CONNECTION.prepareStatement(sql)){
            statement.setString(1, id);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                television = Optional.of(setFieldsToObject(resultSet));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return television;
    }
    public List<Television> findByInvioceId(String invioceId) {
        String sql = "SELECT * FROM \"public\".\"Television\" WHERE \"invoiceID\" = ?";
        Optional<Television> television = Optional.empty();
        List<Television> result = new ArrayList<>();
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

    public List<Television> findWithInvoiceIdNull() {
        String sql = "SELECT * FROM \"public\".\"Phone\" WHERE \"invoiceID\" = NULL";
        Optional<Television> television = Optional.empty();
        List<Television> result = new ArrayList<>();
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
