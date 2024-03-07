@import hexlet.code.model.UrlPage
@param UrlPage page


@template.layout.page(
page = page,
content = @`
    <div class="container-lg mt-5">
        <h1>Сайт: ${page.getUrl().getName()}</h1>
    </div>
`
)