var canvas = document.querySelector('canvas')
var header = document.querySelector('header')
var ctx = canvas.getContext('2d')

// 这里如果要和css配合，不是很尴尬？
var width = canvas.width = header.offsetWidth
console.log('windowWidth : ' + window.width + ' | headerWidth : ' + width)
var height = canvas.height = 200

function random (min, max) {
  var num = Math.floor(Math.random() * (max - min)) + min
  return num
}

function Ball (x, y, velX, velY, color, size) {
  this.x = x
  this.y = y
  this.velX = velX
  this.velY = velY
  this.color = color
  this.size = size
}

Ball.prototype.draw = function () {
  ctx.beginPath()
  ctx.fillStyle = this.color
  ctx.arc(this.x, this.y, this.size, 0, 2 * Math.PI)
  ctx.fill()
}

Ball.prototype.update = function () {
  if ((this.x + this.size + this.velX) >= width) {
    this.velX = 0
  }

  if ((this.x - this.size + this.velX) <= 0) {
    this.velX = 0
  }

  if ((this.y + this.size + this.velY) >= height) {
    this.velY = 0
  }

  if ((this.y - this.size + this.velY) <= 0) {
    this.velY = 0
  }

  this.x += this.velX
  this.y += this.velY
}

function loop () {
  var sideBallNum = 0

  for (var i = 0; i < ballMaxNum; i++) {
    balls[i].draw()
    balls[i].update()

    if (balls[i].velX === 0) {
      sideBallNum++
    }
  }

  if (sideBallNum != ballMaxNum) {
    requestAnimationFrame(loop)
  }
}

var balls = []

/**
 * '#212121'
 * '#ffffff'
 */
var color = ['#1976d2', '#DDBEFB', '#2196f3', '#ff5722', '#757575', '#bdbdbd']
var ballMaxNum = color.length

for (var i = 0; i < ballMaxNum; i++) {
  var ballHeight = random(50, 100) / 100 * height / ballMaxNum
  var ballY = height / ballMaxNum * i + ballHeight / 2
  var colorIndex = random(0, color.length)

  var ball = new Ball(
    random(0, width),
    ballY,
    random(-7, 7),
    0,
    color[colorIndex],
    ballHeight / 2
  )

  color[colorIndex] = color.pop()
  balls.push(ball)
}

loop()
