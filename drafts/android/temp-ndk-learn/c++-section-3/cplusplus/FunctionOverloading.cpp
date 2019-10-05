//
// Created by Jesson on 2019-01-17.
//

#include "FunctionOverloading.h"
#include <cstdio>

using namespace std;

void FunctionOverloading::print() const {
    printf("%g + %g\n", m_real, m_imag);
}

FunctionOverloading FunctionOverloading::operator+(const FunctionOverloading &A) const {
    return FunctionOverloading(m_real + A.m_real, m_imag + A.m_imag);
}

int main() {
    FunctionOverloading A(1.24, 5.7), B(4, 3.14), C;
    C = A + B;
    C.print();
    return 0;
}