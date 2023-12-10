
var deployed = false

// 看java 代码的 ConfigConstant.java 部分
var baseUrl = deployed ? "http://www.basenoodle.com/" : "http://127.0.0.1:8080/"

//访问
var accessUrl = baseUrl + "Access";