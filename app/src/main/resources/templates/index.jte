@import hexlet.code.util.NamedRoutes

@template.layout.page(
content = @`
    <main>
        <h1>Добро пожаловать!</h1>
        <form action="${NamedRoutes.urlsPath()}" method="post">
            <div>
                <label>
                    <input type="text" name="url" value="Введите адрес сайта"/>
                </label>
            </div>
            <input type="submit" value="Добавить" />
        </form>
    </main>
`
)