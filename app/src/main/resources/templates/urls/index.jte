@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
content = @`
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
