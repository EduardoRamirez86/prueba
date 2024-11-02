package controller;

import dao.PeliculaDAO;
import model.Pelicula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/pelicula")
public class PeliculaServlet extends HttpServlet {

    private final PeliculaDAO peliculaDAO = new PeliculaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String genero = request.getParameter("genero");
        String clasificacion = request.getParameter("clasificacion");
        String formato = request.getParameter("formato");
        double valorTerceraEdad = Double.parseDouble(request.getParameter("valorTerceraEdad"));
        double valorAdulto = Double.parseDouble(request.getParameter("valorAdulto"));

        Pelicula pelicula = new Pelicula(nombre, genero, clasificacion, formato, valorTerceraEdad, valorAdulto);
        peliculaDAO.agregarPelicula(pelicula);

        mostrarPeliculas(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mostrarPeliculas(request, response);
    }

    private void mostrarPeliculas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pelicula> peliculas = peliculaDAO.listarPeliculas();
        request.setAttribute("peliculas", peliculas);
        request.getRequestDispatcher("/pelicula.jsp").forward(request, response);
    }
}




