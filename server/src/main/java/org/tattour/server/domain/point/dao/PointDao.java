package org.tattour.server.domain.point.dao;

import java.util.ArrayList;
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

    public List<GetPointChargeRequestRes> getPointChargeRequestResList(Integer userId, Boolean isCompleted){
        String query =
                "SELECT * "
                + "FROM point_charge_request "
                + "WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if(userId != null){
            params.add(userId);
            query += "AND user_id = ? ";
        }

        if(isCompleted != null){
            params.add(isCompleted);
            query += "AND is_completed = ? ";
        }

        return jdbcTemplate.query(query,
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
    }
}
