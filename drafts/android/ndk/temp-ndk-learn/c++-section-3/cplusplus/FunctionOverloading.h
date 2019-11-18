//
// Created by Jesson on 2019-01-17.
//

#ifndef CPLUSPLUS_运算符重载_H
#define CPLUSPLUS_运算符重载_H


class FunctionOverloading {
public:
    //FunctionOverloading(double real ,double imag);
    FunctionOverloading(double real = 0.0, double imag = 0.0) : m_real(real), m_imag(imag) {}

    void print() const;
//    JpegDecoder& operator=(const JpegDecoder &); // assign disabled
    FunctionOverloading operator+(const FunctionOverloading &A) const;

private:
    double m_real;
    double m_imag;
};


#endif //CPLUSPLUS_运算符重载_H
