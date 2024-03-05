package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import hexlet.code.model.BasicPage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,37,37,37,37,44,44,44,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BasicPage page, Content content) {
		jteOutput.writeContent("\r\n<!doctype html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>\r\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\r\n    <link href=\"/\" rel=\"stylesheet\">\r\n    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL\" crossorigin=\"anonymous\"></script>\r\n    <title>Анализатор страниц</title>\r\n</head>\r\n\r\n<body class=\"d-flex flex-column min-vh-100\">\r\n<nav class=\"ml-1 navbar navbar-expand-lg navbar-dark bg-dark\">\r\n    <a class=\"navbar-brand p-2\" href=\"/\">Анализатор страниц</a>\r\n<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n    <span class=\"navbar-toggler-icon\"></span>\r\n</button>\r\n<div class=\"collapse navbar-collapse\" id=\"navbarNav\">\r\n    <ul class=\"navbar-nav\">\r\n        <li class=\"nav-item active\">\r\n            <a class=\"nav-link\" href=\"/\">Главная</a>\r\n        </li>\r\n        <li class=\"nav-item\">\r\n            <a class=\"nav-link\" href=\"/urls\">Сайты</a>\r\n        </li>\r\n    </ul>\r\n</div>\r\n</nav>\r\n\r\n\r\n<main class=\"flex-grow-1\">\r\n    ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n</main>\r\n\r\n<footer class=\"footer border-top py-1 mt-4 bg-light\">\r\n    <p class=\"text-dark bg-muted text-center\">by <a href=\"https://github.com/dtarakanova\" class=\"text-info\">Pelmen</a></p>\r\n</footer>\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BasicPage page = (BasicPage)params.getOrDefault("page", null);
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, page, content);
	}
}
