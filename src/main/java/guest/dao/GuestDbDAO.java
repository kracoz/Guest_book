package guest.dao;

import guest.entity.Guest;
import guest.exception.GuestDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 08.10.2018.
 */
public class GuestDbDAO implements GuestDAO {
    private static final String SELECT
            = "SELECT guest_id, first_name, last_name, phone, email, room FROM jc_guest ORDER BY first_name, last_name";
    private static final String SELECT_ONE
            = "SELECT guest_id, first_name, last_name, phone, email, room FROM jc_guest WHERE guest_id=?";
    private static final String INSERT
            = "INSERT INTO jc_guest (first_name, last_name, phone, email, room) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE
            = "UPDATE jc_guest SET first_name=?, last_name=?, phone=?, email=?, room=? WHERE guest_id=?";
    private static final String DELETE
            = "DELETE FROM jc_guest WHERE guest_id=?";

    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long addGuest(Guest guest) throws GuestDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"guest_id"})) {
            Long guestId = -1L;
            pst.setString(1, guest.getFirstName());
            pst.setString(2, guest.getLastName());
            pst.setString(3, guest.getPhone());
            pst.setString(4, guest.getEmail());
            pst.setLong(5, guest.getRoomNumber());
            pst.executeUpdate();
            ResultSet res = pst.getGeneratedKeys();
            if (res.next()) {
                guestId = res.getLong("guest_id");
            }
            res.close();
            return guestId;
        } catch (Exception e) {
            throw new GuestDaoException(e);
        }
    }

    @Override
    public void updateGuest(Guest guest) throws GuestDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, guest.getFirstName());
            pst.setString(2, guest.getLastName());
            pst.setString(3, guest.getPhone());
            pst.setString(4, guest.getEmail());
            pst.setLong(5, guest.getRoomNumber());
            pst.setLong(6, guest.getGuestId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new GuestDaoException(e);
        }
    }

    @Override
    public void deleteGuest(Long guestId) throws GuestDaoException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, guestId);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new GuestDaoException(e);
        }
    }

    @Override
    public Guest getGuest(Long guestId) throws GuestDaoException {
        Guest guest = null;
        try (Connection con = getConnection()) {
            PreparedStatement pst = con.prepareStatement(SELECT_ONE);
            pst.setLong(1, guestId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                guest = fillGuest(rs);
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            throw new GuestDaoException(e);
        }
        return guest;
    }

    @Override
    public List<Guest> findGuests() throws GuestDaoException {
        List<Guest> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillGuest(rs));
            }
        } catch (Exception e) {
            throw new GuestDaoException(e);
        }
        return list;
    }

    private Guest fillGuest(ResultSet rs) throws SQLException {
        Guest guest = new Guest();
        guest.setGuestId(rs.getLong("guest_id"));
        guest.setFirstName(rs.getString("first_name"));
        guest.setLastName(rs.getString("last_name"));
        guest.setPhone(rs.getString("phone"));
        guest.setEmail(rs.getString("email"));
        guest.setRoomNumber((long)rs.getInt("room"));
        return guest;
    }
}
