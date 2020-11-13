<!doctype html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <style>
        html {
            font-size: 12px;
            background-color: white
        }
        table, th, td {
            border: none;
        }
        p {
            display: block;
        }
        .column100 {
            /*font-size: 3.0mm;*/
            width: 100%;
            margin-bottom: 10px;
        }
        .column70 {
            float: left;
            /*font-size: 2.5mm;*/
            width: 70%;
        }
        .column30-right {
            float: left;
            text-align: right;
            /*font-size: 2.5mm;*/
            width: 30%;
        }
        .bdBottom {
            border-bottom: 1px solid black;
        }
        .bdLeft {
            border-left: 1px solid black;
        }
        .divTabela {
            margin: -1px;
            padding: -1px;
        }
        .bloco {
            margin-bottom: 10px;
        }
        .bloco-margin-top {
            margin-top: 10px;
        }
        .small-font {
            font-size: 10px;
        }
        .content-center{
            align-content: center;
        }
    </style>
</head>
<header>
    <script>
        function substitutePdfVariables() {

            function getParameterByName(name) {
                var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
                return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
            }

            function substitute(name) {
                var value = getParameterByName(name);
                var elements = document.getElementsByClassName(name);

                for (var i = 0; elements && i < elements.length; i++) {
                    elements[i].textContent = value;
                }
            }

            ['topage', 'page']
                .forEach(function(param) {
                    substitute(param);
                });
        }
    </script>
</header>
<body onload="substitutePdfVariables()" style=" padding-top: 5mm">
<div style="border: 0.5mm solid black; padding: 10px;">
    <table>
        <tbody>
        <div class="column100">
            <h2>Basis Tecnologia da Informação - Treinamento Singular</h2>
        </div>
        <div class="column100">
            <b>Solicitante: ${solicitante}</b>
        </div>
        <div class="column100">
            <b>Período: ${dataInicio} - ${dataFim}</b>
        </div>

        <div class="column100">
            <b>Vender dias: ${venderFerias}</b>
        </div>
        <div class="column100">
            <b>Quantidade de dias vendidos: ${quantidadeDias}</b>
        </div>
        <div class="column100">
            <b>Antecipar décimo terceiro: ${anteciparDecimoTerceiro}</b>
        </div>
        </tbody>
    </table>
</div>
</body>
</html>