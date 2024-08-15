package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.UrlsPage;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.UtilsDateTime;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,5,5,7,7,9,9,11,11,11,14,14,19,19,21,21,31,31,34,34,34,35,35,35,35,35,35,35,35,35,35,35,35,36,36,36,37,37,37,40,40,46,46,46,47,47,47,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <main class=\"flex-grow-1\">\n    ");
				if (page.getFlash() != null) {
					jteOutput.writeContent("\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-success\" role=\"alert\">\n            <p class=\"m-0\">");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(page.getFlash());
					jteOutput.writeContent("</p>\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n        </div>\n    ");
				}
				jteOutput.writeContent("\n        <section>\n\n            <div class=\"container-lg mt-5\">\n                <h1>Сайты</h1>\n    ");
				if (page.getUrls().isEmpty()) {
					jteOutput.writeContent("\n        <p>Пока не добавлено ни одного адреса сайта</p>\n    ");
				}
				jteOutput.writeContent("\n                <table class=\"table table-bordered table-hover mt-3\">\n                    <thead>\n                    <tr>\n                        <th scope=\"col\">#</th>\n                        <th scope=\"col\">Имя</th>\n                        <th scope=\"col\">Последняя проверка</th>\n                        <th scope=\"col\">Код ответа</th>\n                    </tr>\n                    </thead>\n                    ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                        <tbody>\n                        <tr>\n                            <th scope=\"row\">");
					jteOutput.setContext("th", null);
					jteOutput.writeUserContent(page.getUrls().indexOf(url) + 1);
					jteOutput.writeContent("</th>\n                            <td><a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a></td>\n                            <th scope=\"row\">");
					jteOutput.setContext("th", null);
					jteOutput.writeUserContent(url.getUrlChecks().isEmpty() ? "" : UtilsDateTime.dateFormatter(url.getUrlChecks().getLast().getCreatedAt()));
					jteOutput.writeContent("</th>\n                            <th scope=\"row\">");
					jteOutput.setContext("th", null);
					jteOutput.writeUserContent(url.getUrlChecks().isEmpty() ? "" : String.valueOf(url.getUrlChecks().getLast().getStatusCode()));
					jteOutput.writeContent("</th>\n                        </tr>\n                        </tbody>\n                    ");
				}
				jteOutput.writeContent("\n                </table>\n            </div>\n\n        </section>\n    </main>\n");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
