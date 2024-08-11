@import hexlet.code.dto.UrlPage
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.layout.page(
content = @`
    <h1>${page.getUrl().getName()}</h1>
`
)