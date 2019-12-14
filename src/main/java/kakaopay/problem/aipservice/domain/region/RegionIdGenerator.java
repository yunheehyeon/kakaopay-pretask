package kakaopay.problem.aipservice.domain.region;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionIdGenerator implements IdentifierGenerator {
    private static final Logger log = LoggerFactory.getLogger(RegionIdGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Connection connection = session.connection();
        String sql = "select count(region_id) as id from region";

        try (
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return createId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            log.error("{}", e);
            throw new HibernateException("RegionId 생성 불가");
        }

        return null;
    }

    private String createId(int id) {
        String prefix = "reg";
        String code = prefix + StringUtils.leftPad("" + id, 4, '0');
        log.debug("code : {}", code);

        return code;
    }
}
