@import hexlet.code.model.UrlsPage
@param UrlsPage page

!{var formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");}

@template.layout.page(
page = page,
content = @`
    <div class="container-lg mt-5">
        <h1>Сайты</h1>

        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th class="col-1">ID</th>
                <th>Имя</th>
                <th class="col-2">Последняя проверка</th>
                <th class="col-1">Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(var url : page.getAllUrls())

                <tr>
                    <td>
                        ${url.getId()}
                    </td>
                    <td>
                        <a href="/urls/" + id>${url.getName()}</a>
                    </td>
                    <td>
                        Test 1
                    </td>
                    <td>
                        Test 2
                    </td>

                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`
)