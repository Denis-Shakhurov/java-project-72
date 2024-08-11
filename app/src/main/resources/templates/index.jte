@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.BuildUrlPage
@param BuildUrlPage page

@template.layout.page(
content = @`
    <main>
    @if(page.getFlash() != null)
        <div class="p-3 mb-2 bg-gradient-success text-success">${page.getFlash()}</div>
    @endif
        <h1>Добро пожаловать!</h1>
        <form action="${NamedRoutes.urlsPath()}" method="post">
            <div>
                <label>
                    <input type="text" name="url" value="${page.getName()}"/> <input type="submit" value="Добавить" />
                </label>
            </div>
        </form>
    </main>
`
)