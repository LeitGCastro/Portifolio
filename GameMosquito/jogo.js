
var altura = 0 
var largura = 0 
var vidas = 1
var tempo = 50

var criaTempoMosquito = 1500

//" search " recupera apenas a query string do url
var nivel = window.location.search
nivel.replace('?', '')

// configura a dificuldade do jogo de acordo com o aparecimento dos mosquitos
if (nivel === 'normal') {
    criaTempoMosquito = 1500
} else if (nivel === 'dificil') {
    criaTempoMosquito = 1000    
} else if (nivel === 'chucknorris'){
    criaTempoMosquito = 750 
}


function ajustaTamanho(){
    altura = window.innerHeight 
    largura = window.innerWidth
    console.log(altura, largura)
}
ajustaTamanho()

// "variavel" = setInterval(<ação "função de callback">, <tempo em milisegundos>)
var cronometro = setInterval(
(function() { 
    tempo -= 1
    
    if (tempo < 0) {
        // acaba com o loop do interval
        clearInterval(cronometro)
        clearInterval(criaMosquito)
        
        window.location.href = 'vitoria.html'
    }else {
        document.getElementById('cronometro').innerHTML = tempo 
    }
} ), 1000)


function posicaoMosca() {

    //remove o mosquito  - se o elemento for identificado ele será removido
    if (document.getElementById('mosquito') ) {
        document.getElementById('mosquito').remove()

        if (vidas < 3 ) {
            document.getElementById('v' + vidas ).src="imagens/coracao_vazio.png"
            vidas++

        } else {
            window.location.href = 'fim.html'

        }   

    }

    var posicaoX = Math.floor(Math.random() * largura) -90 // -90 é para imagem não ultrapassar o limite da tela
    var posicaoY = Math.floor(Math.random() * altura) -90  

    // para a img não desaparecer caso fique 0 - 90
    posicaoX = posicaoX < 0 ? 0 : posicaoX
    posicaoY = posicaoY < 0 ? 0 : posicaoY

    console.log(posicaoX, posicaoY)

    //criar o elemento html
    var mosquito = document.createElement('img')
    document.body.appendChild(mosquito) // cria o elemento no "mosquito" no body
    mosquito.src = "imagens/mosca.png"
    
    //Stylo 
    mosquito.className = tamanhoAleatorio() + ' ' +ladoAleatorio()
    // posição random
    mosquito.style.left = posicaoX + 'px'
    mosquito.style.top = posicaoY + 'px'
    mosquito.style.position = 'absolute'
    mosquito.id = 'mosquito'
    mosquito.onclick = function() {
        this.remove()        
    }
}


function tamanhoAleatorio() {
    var classe = Math.floor(Math.random() * 3)

    switch(classe) {
        case 0: 
            return 'mosquito1'
        case 1:
            return 'mosquito2'
        case 2:
            return 'mosquito3'

    }
}


function ladoAleatorio() {
    var classe = Math.floor(Math.random() * 2)

    switch(classe) {
        case 0: 
            return 'ladoA'
        case 1:
            return 'ladoB'
    }
}