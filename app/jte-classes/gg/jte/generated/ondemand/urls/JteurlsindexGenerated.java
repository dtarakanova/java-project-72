package gg.jte.generated.ondemand.urls;
import hexlet.code.model.UrlsPage;
import hexlet.code.util.NamedRoutes;
public final class JteurlsindexGenerated {
	public static final String JTE_NAME = "urls/urlsindex.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,4,6,9,9,10,10,11,11,11,11,12,12,12,14,14,27,27,31,31,31,34,34,34,34,34,34,34,34,34,34,34,44,44,48,48,48,48,48,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		var formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		jteOutput.writeContent("\r\n\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    ");
				if (page != null && page.getFlash() != null) {
					jteOutput.writeContent("\r\n        <div class=\"rounded-0 m-0 alert alert-success alert-");
					jteOutput.setContext("div", "class");
					jteOutput.writeUserContent(page.getFlashType());
					jteOutput.setContext("div", null);
					jteOutput.writeContent("\" role=\"alert\">\r\n            ");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("\r\n        </div>\r\n    ");
				}
				jteOutput.writeContent("\r\n       <div class=\"container-lg mt-5\">\r\n        <h1>Сайты</h1>\r\n        <table class=\"table table-bordered table-hover mt-3\">\r\n            <thead>\r\n            <tr>\r\n                <th class=\"col-1\">ID</th>\r\n                <th>Имя</th>\r\n                <th class=\"col-2\">Последняя проверка</th>\r\n                <th class=\"col-1\">Код ответа</th>\r\n            </tr>\r\n            </thead>\r\n            <tbody>\r\n            ");
				for (var url : page.getAllUrls()) {
					jteOutput.writeContent("\r\n\r\n                <tr>\r\n                    <td>\r\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("\r\n                    </td>\r\n                    <td>\r\n                        <a href=\"");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(NamedRoutes.urlsPath());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(url.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a>\r\n                    </td>\r\n                    <td>\r\n                        Test 1\r\n                    </td>\r\n                    <td>\r\n                        Test 2\r\n                    </td>\r\n\r\n                </tr>\r\n            ");
				}
				jteOutput.writeContent("\r\n            </tbody>\r\n        </table>\r\n    </div>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
