package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.UrlPage;
import hexlet.code.util.NamedRoutes;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,10,10,10,16,16,16,20,20,20,24,24,24,30,30,30,30,30,30,30,30,30,43,43,47,47,47,50,50,50,53,53,53,56,56,56,59,59,59,62,62,62,67,67,72,72,72,72,72,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <section>\n\n        <div class=\"container-lg mt-5\">\n            <h1>");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\n\n            <table class=\"table table-bordered table-hover mt-3\">\n                <tbody>\n                <tr>\n                    <td>ID</td>\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\n                </tr>\n                <tr>\n                    <td>Имя</td>\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\n                </tr>\n                <tr>\n                    <td>Дата создания</td>\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().dateFormat(page.getUrl().getCreatedAt()));
				jteOutput.writeContent("</td>\n                </tr>\n                </tbody>\n            </table>\n\n            <h2 class=\"mt-5\">Проверки</h2>\n            <form method=\"post\"");
				var __jte_html_attribute_0 = NamedRoutes.checksUrlPath(page.getUrl().getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n                <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\n            </form>\n\n            <table class=\"table table-bordered table-hover mt-3\">\n                <thead>\n                <tr><th class=\"col-1\">ID</th>\n                    <th class=\"col-1\">Код ответа</th>\n                    <th>title</th>\n                    <th>h1</th>\n                    <th>description</th>\n                    <th class=\"col-2\">Дата проверки</th>\n                </tr></thead>\n                ");
				for (var checkUrl : page.getUrl().getUrlChecks()) {
					jteOutput.writeContent("\n                <tbody>\n                <tr>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(checkUrl.getId());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(checkUrl.getStatusCode());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(checkUrl.getTitle());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(checkUrl.getH1());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(checkUrl.getDescription());
					jteOutput.writeContent("\n                    </td>\n                    <td>\n                        ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(page.getUrl().dateFormat(checkUrl.getCreatedAt()));
					jteOutput.writeContent("\n                    </td>\n                </tr>\n\n                </tbody>\n                ");
				}
				jteOutput.writeContent("\n            </table>\n        </div>\n\n    </section>\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
