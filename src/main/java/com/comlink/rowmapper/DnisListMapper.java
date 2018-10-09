package com.comlink.rowmapper;

 

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.comlink.model.DNIS;

public class DnisListMapper implements RowMapper<DNIS>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public DNIS mapRow(ResultSet resultSet, int rowCount) throws SQLException {
		DNIS dnis = new DNIS();
		dnis.setRecordid(resultSet.getString("RecordID"));
		dnis.setDNIS(resultSet.getString("DNIS"));
		dnis.setPrefix(resultSet.getString("Prefix"));
		dnis.setMnumber(resultSet.getString("MappedNumber"));
		dnis.setCname(resultSet.getString("CustomerName"));
		dnis.setRip1(resultSet.getString("RoutingIP1"));
		dnis.setRip2(resultSet.getString("RoutingIP2"));
		dnis.setRip3(resultSet.getString("RoutingIP3"));
		dnis.setRip4(resultSet.getString("RoutingIP4"));
		dnis.setRip5(resultSet.getString("RoutingIP5"));
		dnis.setRip6(resultSet.getString("RoutingIP6"));
		dnis.setDatec(resultSet.getString("DateCreated"));
		dnis.setDatem(resultSet.getString("DateModified"));
		dnis.setTicketno(resultSet.getString("TicketOrderNum"));
		dnis.setDtfs(resultSet.getString("DTFS"));
		dnis.setStatus(resultSet.getString("Status"));
		dnis.setFile(resultSet.getString("Filename"));
		
		dnis.setUserId(resultSet.getString("userID"));
		dnis.setDidNumber(resultSet.getString("DIDnumber"));
		dnis.setGatewayName(resultSet.getString("GatewayGroupName"));
		dnis.setInitialAction(resultSet.getString("InitialActionBy"));
		dnis.setCurrentAction(resultSet.getString("CurrentAction"));
		
		
		return dnis;
	}

}
