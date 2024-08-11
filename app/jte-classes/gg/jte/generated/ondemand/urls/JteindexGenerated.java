package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.UrlsPage;
import hexlet.code.util.NamedRoutes;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,9,9,11,11,19,19,22,22,22,23,23,23,23,23,23,23,23,23,23,23,23,27,27,29,29,30,30,30,31,31,31,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <h1>Список сайтов</h1>\n\n    ");
				if (page.getUrls().isEmpty()) {
					jteOutput.writeContent("\n        <p>Пока не добавлено ни одного адреса сайта</p>\n    ");
				} else {
					jteOutput.writeContent("\n        <table class=\"table table-sm table-striped\">\n            <thead class=\"thead-dark\">\n            <tr>\n                <th scope=\"col\">#</th>\n                <th scope=\"col\">Имя</th>\n            </tr>\n            </thead>\n            ");
					for (var url : page.getUrls()) {
						jteOutput.writeContent("\n                <tbody class=\"table-striped\">\n                <tr>\n                    <th scope=\"row\">");
						jteOutput.setContext("th", null);
						jteOutput.writeUserContent(page.getUrls().indexOf(url) + 1);
						jteOutput.writeContent("</th>\n                    <td><a");
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
						jteOutput.writeContent("</a></td>\n\n                </tr>\n                </tbody>\n            ");
					}
					jteOutput.writeContent("\n        </table>\n    ");
				}
				jteOutput.writeContent("\n");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
