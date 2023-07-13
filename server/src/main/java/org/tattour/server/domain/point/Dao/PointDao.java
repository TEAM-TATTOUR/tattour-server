package org.tattour.server.domain.point.Dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestRes;

@Repository
public class PointDao {
    private final JdbcTemplate jdbcTemplate;

    public PointDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetPointChargeRequestRes> getPointChargeRequestResList(Integer userId, Boolean isComplete){
        String query =
                "SELECT * "
                + "FROM point_charge_request "
                + "WHERE 1=1 ";
        List<Object> params = List.of(userId, isComplete);

        if(userId != null)
            query += "AND user_id = ? ";

        if(isComplete != null)
            query += "AND is_completed = ?";

        List<GetPointChargeRequestRes> getPointChargeRequestResList =
                jdbcTemplate.query(query,
                        (rs, rownum) ->  GetPointChargeRequestRes.builder()
                                .id(rs.getInt("id"))
                                .userId(rs.getInt("user_id"))
                                .chargeAmount(rs.getInt("charge_amount"))
                                .transferredAmount(rs.getInt("transferred_amount"))
                                .isDeposited(rs.getBoolean("is_deposited"))
                                .isAmountMatched(rs.getBoolean("is_amount_matched"))
                                .isCompleted(rs.getBoolean("is_completed"))
                                .createdAt(rs.getString("created_at"))
                                .lastUpdatedAt(rs.getString("last_updated_at"))
                                .state(rs.getBoolean("state"))
                                .build(),
                        params.toArray());

        return getPointChargeRequestResList;
    }
}
