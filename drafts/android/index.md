源码：http://androidxref.com/


编辑器：https://androidpal.com/studio/about  
移动应用质量优化：https://github.com/didi/booster    
how to root: https://www.xda-developers.com/root/  

### 实际问题
项目中，聊天快速发送，不断的add 和 scrollToBottom，
然后消息收到状态变更回调，不断的notifyItemInsert，会造成崩溃。
但是如果最后是notifyDataSetChanged 就没有问题
