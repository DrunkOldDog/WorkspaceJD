package mypackage1;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import oracle.jdbc.*;
import java.util.*;


public class editobrasAction extends Action 
{
  /**
   * This is the main action called from the Struts framework.
   * @param mapping The ActionMapping used to select this instance.
   * @param form The optional ActionForm bean for this request.
   * @param request The HTTP Request we are processing.
   * @param response The HTTP Response we are processing.
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    editobrasActionForm cc = (editobrasActionForm) form;
    String cod = cc.getCodigo();
    
    Connection cn = null;
    ConnectDB conn =new ConnectDB();
    ResultSet rsConsulta = null;

       try
       {
         cn = conn.conexion;
         String cadena = "select o.idobra,o.titulo,o.descripcion,o.precio,e.titulo as EXPOSICION,p.nombre,p.apellido from jd_obra o, jd_propietario p,jd_exposicion e, jd_expobra ex where o.idpropietario = p.idpropietario and o.vendida=0 and e.idexposicion = ex.idexposicion and o.idobra = ex.idobra and o.IDOBRA ="+cod;
         System.out.println(cadena);
         rsConsulta = conn.getData(cadena);
         if(rsConsulta.next()){
         request.getSession().setAttribute("ocod",rsConsulta.getString("IDOBRA"));
         request.getSession().setAttribute("otit",rsConsulta.getString("TITULO"));
         request.getSession().setAttribute("odes",rsConsulta.getString("DESCRIPCION"));
         request.getSession().setAttribute("opre",rsConsulta.getString("PRECIO"));
         request.getSession().setAttribute("oexp",rsConsulta.getString("EXPOSICION"));
         request.getSession().setAttribute("onom",rsConsulta.getString("NOMBRE")+" "+rsConsulta.getString("APELLIDO"));
         }else{
              request.getSession().setAttribute("error","La Obra con el ID: "+cod+" ya fue vendida o no se encuentra disponible");
              return mapping.findForward("malo");
         }
         System.out.println("done mfdkasdas");
         return mapping.findForward("editar");
	      }
	
        catch(Exception e)
       {
          e.printStackTrace();
          return (mapping.findForward("malo"));
       }
       
    finally
    {
      conn.closeConnection();	
    }
  }
}