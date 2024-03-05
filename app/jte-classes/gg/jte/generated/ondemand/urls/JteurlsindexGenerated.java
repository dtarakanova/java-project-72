package gg.jte.generated.ondemand.urls;
import hexlet.code.model.UrlsPage;
public final class JteurlsindexGenerated {
	public static final String JTE_NAME = "urls/urlsindex.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,3,5,8,8,22,22,26,26,26,29,29,29,39,39,43,43,43,43,43,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		var formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		jteOutput.writeContent("\r\n\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"container-lg mt-5\">\r\n        <h1>Сайты</h1>\r\n\r\n        <table class=\"table table-bordered table-hover mt-3\">\r\n            <thead>\r\n            <tr>\r\n                <th class=\"col-1\">ID</th>\r\n                <th>Имя</th>\r\n                <th class=\"col-2\">Последняя проверка</th>\r\n                <th class=\"col-1\">Код ответа</th>\r\n            </tr>\r\n            </thead>\r\n            <tbody>\r\n            ");
				for (var url : page.getAllUrls()) {
					jteOutput.writeContent("\r\n\r\n                <tr>\r\n                    <td>\r\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("\r\n                    </td>\r\n                    <td>\r\n                        <a href=\"/urls/\" + id>");
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
