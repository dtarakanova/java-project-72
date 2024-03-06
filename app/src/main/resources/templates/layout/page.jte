@import gg.jte.Content
@import hexlet.code.model.BasicPage
@import hexlet.code.util.NamedRoutes

@param BasicPage page = null
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="/" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <title>Анализатор страниц</title>
</head>

<body class="d-flex flex-column min-vh-100">
<nav class="ml-1 navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand p-2" href="/">Анализатор страниц</a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
</button>
<div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="${NamedRoutes.mainPath()}">Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${NamedRoutes.urlsPath()}">Сайты</a>
        </li>
    </ul>
</div>
</nav>
<main class="flex-grow-1">
    @if(page != null && page.getFlash() != null)
        <div class="rounded-0 m-0 alert alert-dismissible fade show alert-${page.getFlashType()}" role="alert">
            <p class="m-0">${page.getFlash()}</p>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    @endif
    <section>
        ${content}
    </section>
</main>
<footer class="footer border-top py-1 mt-4 bg-light">
    <p class="text-dark bg-muted text-center">by <a href="https://github.com/dtarakanova" class="text-info">Pelmen</a></p>
</footer>
</body>
</html>