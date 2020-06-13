```assembly
public class Temp {
  public static volatile int race;

  public Temp();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void increase();
    Code:
       // 操作栈存入race
       0: getstatic     #7                  // Field race:I
       // 操作栈存入1
       3: iconst_1
       // 操作栈执行 race + 1,并且移除两者，放入结果
       4: iadd
       // 
       5: putstatic     #7                  // Field race:I
       8: return

  public static void main(java.lang.String[]);
    Code:
       // 初始化0 ？
       0: iconst_0
       // 
       1: istore_1
       2: iload_1
       3: bipush        20
       5: if_icmpge     31
       8: new           #13                 // class java/lang/Thread
      11: dup
      12: new           #15                 // class Temp$1
      15: dup
      16: invokespecial #17                 // Method Temp$1."<init>":()V
      19: invokespecial #18                 // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
      22: invokevirtual #21                 // Method java/lang/Thread.start:()V
      25: iinc          1, 1
      28: goto          2
      31: invokestatic  #24                 // Method java/lang/Thread.activeCount:()I
      34: iconst_1
      35: if_icmple     44
      38: invokestatic  #28                 // Method java/lang/Thread.yield:()V
      41: goto          31
      44: return

  static {};
    Code:
       0: iconst_0
       1: putstatic     #7                  // Field race:I
       4: return
}
```

