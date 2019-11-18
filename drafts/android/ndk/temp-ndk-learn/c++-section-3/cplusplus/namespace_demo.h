
#ifndef C_NAMESPACE_DEMO_H
#define C_NAMESPACE_DEMO_H

//class
class namespace_demo {

};
//namespace
namespace NameSpaceA{
    int name = 0;
}

namespace NameSpaceB{
    int age = 0;
    namespace NameSpaceC
    {
        struct Teacher
        {
            char name[10];
            int age;
        };
    }
}




#endif //C_NAMESPACE_DEMO_H
