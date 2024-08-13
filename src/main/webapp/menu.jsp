<%-- 
    Document   : Menu
    Created on : 1 de jul de 2024, 22:17:16
    Author     : 17588831626
--%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <title>Página Principal</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            nav {
                background-color: #333;
                color: white;
                padding: 10px 0;
            }

            nav ul {
                list-style: none;
                padding: 0;
                margin: 0;
                display: flex;
                justify-content: center;
            }

            nav ul li {
                margin: 0 15px;
            }

            nav ul li a {
                color: white;
                text-decoration: none;
                font-weight: bold;
                padding: 10px 20px;
                transition: background-color 0.3s;
            }

            nav ul li a:hover {
                background-color: #555;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">Cidade</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar">Funcionário</a></li>
                <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/LogoutControlador">Logout</a></li>
            </ul>
        </nav>
    </body>
</html>
