package kr.co.hk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DAO {
	private static Connection getCon() throws Exception{
		Connection con = null;	
		Class.forName("oracle.jdbc.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
											"pizza",
											"hkit2019");
		System.out.println("연결성공!");
		return con;
	}	
	
	private static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try { rs.close(); } catch (SQLException e) {  e.printStackTrace(); }
		}
		
		if(ps != null) {
			try { ps.close(); } catch (SQLException e) {  e.printStackTrace(); }
		}
		
		if(con != null) {
			try { con.close(); } catch (SQLException e) {  e.printStackTrace(); }
		}
	}
	public static int getInsertSaleNo() {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		String sql = " SELECT nvl(max(saleno), 0) + 1 FROM tbl_salelist_01 ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return result;
	}
	
	//피자 종류 리스트
	public static List<PizzaVo> selectPizzaList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		List<PizzaVo> list = new ArrayList<PizzaVo>();
		String sql = " SELECT * FROM tbl_pizza_01 ";		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				vo.setPcode(rs.getString("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setCost(rs.getInt("cost"));				
				list.add(vo);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}		
		return list;
	}
	
	//지점 리스트
	public static List<PizzaVo> selectShopList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;			
		List<PizzaVo> list = new ArrayList<PizzaVo>();
		String sql = " SELECT * FROM tbl_shop_01 ";			
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));									
				list.add(vo);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}			
		return list;
	}
	
	//통합매출현황조회
	public static List<PizzaVo> selectTotalSaleList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<PizzaVo> list = new ArrayList<PizzaVo>();
		
		try {
			con = getCon();
			String sql = " select a.saleno, c.scode, c.sname, to_char(a.saledate, 'YYYY-MM-DD') as saledate, a.pcode, "
					+ " b.pname, a.amount, (b.cost * a.amount) as total " + 
					" from tbl_salelist_01 a " + 
					" left join TBL_PIZZA_01 b " + 
					" on a.pcode = b.pcode " + 
					" left join TBL_SHOP_01 c " + 
					" on a.scode = c.scode " + 
					" order by a.saleno asc ";
			
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				
				vo.setSaleno(rs.getInt("saleno"));
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setSaledate(rs.getString("saledate"));
				vo.setPcode(rs.getString("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setAmount(rs.getInt("amount"));
				vo.setTotal(rs.getInt("total"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		
		return list;
	}

	//지점별 매출현황
	public static List<PizzaVo> selectShopSaleList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<PizzaVo> list = new ArrayList<PizzaVo>();
		
		try {
			con = getCon();
			/*
		String sql = " select c.scode, c.sname, sum((b.cost * a.amount))as total " + 
				" from tbl_salelist_01 a " + 
				" left join TBL_PIZZA_01 b " + 
				" on a.pcode = b.pcode " + 
				" left join TBL_SHOP_01 c " + 
				" on a.scode = c.scode " + 
				" group by c.SCODE, c.SNAME " + 
				" order by c.SCODE ";
				*/
			
			/*

				select B.scode, B.sname, A.total
				FROM
				(
				    select scode, sum(price) as total
				    from 
				    (
				    	SELECT
				        a.scode, (a.amount * b.cost) as price
				        FROM tbl_salelist_01 A
				        INNER JOIN tbl_pizza_01 B
				        ON A.pcode = B.pcode
				    )
				    group by scode
				) A
				INNER JOIN tbl_shop_01 B
				ON A.scode = B.scode
				ORDER BY A.scode ASC 

			 */
			String sql = "select B.scode, B.sname, A.total\r\n" + 
					"FROM\r\n" + 
					"(\r\n" + 
					"    select scode, sum(price) as total\r\n" + 
					"    from (\r\n" + 
					"    SELECT\r\n" + 
					"        a.scode, (a.amount * b.cost) as price\r\n" + 
					"        FROM tbl_salelist_01 A\r\n" + 
					"        INNER JOIN tbl_pizza_01 B\r\n" + 
					"        ON A.pcode = B.pcode\r\n" + 
					"    )\r\n" + 
					"    group by scode\r\n" + 
					") A\r\n" + 
					"INNER JOIN tbl_shop_01 B\r\n" + 
					"ON A.scode = B.scode\r\n" +
					"ORDER BY A.scode ASC ";

			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		
		while(rs.next()) {
			PizzaVo vo = new PizzaVo();
			
			vo.setScode(rs.getString("scode"));
			vo.setSname(rs.getString("sname"));
			vo.setTotal(rs.getInt("total"));
			list.add(vo);
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}

	//상품별 매출현황
	public static List<PizzaVo> selectProductSale() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PizzaVo> list = new ArrayList<PizzaVo>();
		
		/*
			SELECT
			B.pcode, B.pname, (A.amount * B.cost) as total
			FROM
			(
			    SELECT
			    pcode, sum(amount) as amount
			    FROM tbl_salelist_01
			    GROUP BY pcode
			) A
			INNER JOIN tbl_pizza_01 B
			ON A.pcode = B.pcode
			ORDER BY B.pcode ASC
		 */
		String sql = " select a.pcode, a.pname, sum((a.cost * b.amount))as total " + 
				"from TBL_PIZZA_01 a " + 
				"inner join TBL_SALELIST_01 b " + 
				"on a.pcode = b.pcode " + 
				"group by a.PCODE, a.pname " + 
				"order by total desc ";
		
		System.out.println(sql);
		try {
			con = getCon();			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		
			while(rs.next()) {
				PizzaVo vo = new PizzaVo();
				
				vo.setPcode(rs.getString("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setTotal(rs.getInt("total"));
				list.add(vo);
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		
		return list;
		
	}

}
