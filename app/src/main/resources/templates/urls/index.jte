@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.UtilsDateTime
@param UrlsPage page

@template.layout.page(
content = @`
    <main class="flex-grow-1">
    @if(page.getFlash() != null)
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-success" role="alert">
            <p class="m-0">${page.getFlash()}</p>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    @endif
        <section>

            <div class="container-lg mt-5">
                <h1>Сайты</h1>
    @if(page.getUrls().isEmpty())
        <p>Пока не добавлено ни одного адреса сайта</p>
    @endif
                <table class="table table-bordered table-hover mt-3">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Последняя проверка</th>
                        <th scope="col">Код ответа</th>
                    </tr>
                    </thead>
                    @for(var url : page.getUrls())
                        <tbody>
                        <tr>
                            <th scope="row">${page.getUrls().indexOf(url) + 1}</th>
                            <td><a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a></td>
                            <th scope="row">${page.getUrlChecks().isEmpty() ? "" : UtilsDateTime.dateFormatter(page.getUrlChecks().get(url.getId()).getCreatedAt())}</th>
                            <th scope="row">${page.getUrlChecks().isEmpty() ? "" : String.valueOf(page.getUrlChecks().get(url.getId()).getStatusCode())}</th>
                        </tr>
                        </tbody>
                    @endfor
                </table>
            </div>

        </section>
    </main>
`
)
