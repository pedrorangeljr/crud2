package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoContato;
import model.Contato;


@WebServlet("/ServletsContatos")
public class ServletsContatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoContato daoContato = new DaoContato();
    
    public ServletsContatos() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			String contato = request.getParameter("contato");
			
			if (acao.equalsIgnoreCase("delete")) {
				
				daoContato.deletar(contato);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("principal.jsp");
				request.setAttribute("contatos", daoContato.listar());
				dispatcher.forward(request, response);
				
			}
			else if(acao.equalsIgnoreCase("editar")) {
				
				Contato contatos = daoContato.consultar(contato);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("adicionarContatos.jsp");
				request.setAttribute("contatos", contatos);
				dispatcher.forward(request, response);
				
			}
			else if(acao.equalsIgnoreCase("listarTodos")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("principal.jsp");
				request.setAttribute("contatos", daoContato.listar());
				dispatcher.forward(request, response);
			}
			else if(acao.equalsIgnoreCase("imprimirRelatorio")) {
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			String email = request.getParameter("email");
			
			Contato contato = new Contato();
			
			contato.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			contato.setNome(nome);
			contato.setTelefone(telefone);
			contato.setEmail(email);
			
			if(id == null || id.isEmpty() && !daoContato.validarTelefone(telefone)) {
				
				request.setAttribute("mensagem", "Já existe contato com o mesmo telefone");
				
			}
			else if(id == null || id.isEmpty()) {
				
				daoContato.salvarContato(contato);
			}
			
			else if(id != null && !id.isEmpty()) {
				
				daoContato.atualizar(contato);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("principal.jsp");
			request.setAttribute("contatos", daoContato.listar());
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
