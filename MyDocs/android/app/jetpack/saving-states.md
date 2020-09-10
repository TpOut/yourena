|                                                      | ViewModel                                                    | Saved instance state                                         | Persistent storage                                           |
| :--------------------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| Storage location                                     | in memory                                                    | serialized to disk                                           | on disk or network                                           |
| Survives configuration change                        | Yes                                                          | Yes                                                          | Yes                                                          |
| Survives system-initiated process death              | No                                                           | Yes                                                          | Yes                                                          |
| Survives user complete activity dismissal/onFinish() | No                                                           | No                                                           | Yes                                                          |
| Data limitations                                     | complex objects are fine, but space is limited by available memory | only for primitive types and simple, small objects such as String | only limited by disk space or cost / time of retrieval from the network resource |
| Read/write time                                      | quick (memory access only)                                   | slow (requires serialization/deserialization and disk access) | slow (requires disk access or network transaction)           |



viewModel 只是在configureChange 时保持数据，所以在页面由于内存不足回收导致的重建逻辑中要配合saveInstance。  

而在打开页面的时候，如果intent 已经有传入id 等必须数据，那么在重建activity 的时候，可以直接从intent 里获取而不需要saveInstance  

  

