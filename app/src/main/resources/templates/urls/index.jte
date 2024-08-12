@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
content = @`
    @if(page.getFlash() != null)
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-success" role="alert">
            <p class="m-0">${page.getFlash()}</p>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    @endif
    <h1>Список сайтов</h1>

    @if(page.getUrls().isEmpty())
        <p>Пока не добавлено ни одного адреса сайта</p>
    @else
        <table class="table table-sm table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Имя</th>
            </tr>
            </thead>
            @for(var url : page.getUrls())
                <tbody class="table-striped">
                <tr>
                    <th scope="row">${page.getUrls().indexOf(url) + 1}</th>
                    <td><a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a></td>

                </tr>
                </tbody>
            @endfor
        </table>
    @endif
`
)
