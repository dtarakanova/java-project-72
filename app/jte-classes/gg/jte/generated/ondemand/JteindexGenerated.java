package gg.jte.generated.ondemand;
import hexlet.code.model.MainPage;
import hexlet.code.util.NamedRoutes;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,5,5,8,8,15,15,15,15,15,15,15,15,15,34,34,34,34,34,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MainPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <section>\r\n        <div class=\"container-fluid bg-dark p-5\">\r\n            <div class=\"row\">\r\n                <div class=\"col-md-10 col-lg-8 mx-auto text-light\">\r\n                    <h1 class=\"display-3 mb-0\">Анализатор страниц</h1>\r\n                    <p class=\"lead\">Бесплатно проверяйте сайты на SEO пригодность</p>\r\n                    <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\" class=\"rss-form text-body\">\r\n                        <div class=\"row\">\r\n                            <div class=\"col\">\r\n                            <div class=\"form-floating\">\r\n                            <input id=\"url-input\" autofocus type=\"text\" required name=\"url\" aria-label=\"url\"\r\n                                   class=\"form-control w-80\" placeholder=\"ссылка\" autocomplete=\"off\">\r\n                            <label for=\"url-input\">Ссылка</label>\r\n                            </div>\r\n                            </div>\r\n                            <div class=\"col-auto\">\r\n                                <button type=\"submit\" class=\"h-100 btn btn-lg btn-info px-sm-5\">Проверить</button>\r\n                            </div>\r\n                        </div>\r\n                    </form>\r\n                    <p class=\"text-secondary\"><small>Пример: https://www.example.com</small></p>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </section>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MainPage page = (MainPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
