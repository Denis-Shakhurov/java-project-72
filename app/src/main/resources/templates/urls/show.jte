@import hexlet.code.dto.UrlPage
@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.UtilsDateTime
@param UrlPage page

@template.layout.page(
content = @`
    <section>
        @if(page.getFlash() != null)
            <div class="rounded-0 m-0 alert alert-dismissible fade show alert-warning" role="alert">
                <p class="m-0">${page.getFlash()}</p>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @endif

        <div class="container-lg mt-5">
            <h1>${page.getUrl().getName()}</h1>

            <table class="table table-bordered table-hover mt-3">
                <tbody>
                <tr>
                    <td>ID</td>
                    <td>${page.getUrl().getId()}</td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td>${page.getUrl().getName()}</td>
                </tr>
                <tr>
                    <td>Дата создания</td>
                    <td>${UtilsDateTime.dateFormatter(page.getUrl().getCreatedAt())}</td>
                </tr>
                </tbody>
            </table>

            <h2 class="mt-5">Проверки</h2>
            <form method="post" action="${NamedRoutes.checksUrlPath(page.getUrl().getId())}">
                <button type="submit" class="btn btn-primary">Запустить проверку</button>
            </form>

            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr><th class="col-1">ID</th>
                    <th class="col-1">Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th class="col-2">Дата проверки</th>
                </tr></thead>
                @for(var checkUrl : page.getUrlChecks())
                <tbody>
                <tr>
                    <td>
                        ${checkUrl.getId()}
                    </td>
                    <td>
                        ${checkUrl.getStatusCode()}
                    </td>
                    <td>
                        ${checkUrl.getTitle()}
                    </td>
                    <td>
                        ${checkUrl.getH1()}
                    </td>
                    <td>
                        ${checkUrl.getDescription()}
                    </td>
                    <td>
                        ${UtilsDateTime.dateFormatter(checkUrl.getCreatedAt())}
                    </td>
                </tr>

                </tbody>
                @endfor
            </table>
        </div>

    </section>
`
)