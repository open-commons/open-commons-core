[2019/10/10]
- Add
  + void open.commons.lang.IRunnable.start(boolean daemon)
  + void open.commons.lang.AbstractRunnable.start(boolean daemon)


[2019/10/02]
- Add
  + open.commons.lang.AbstractRunnable
    - public void join() 
    - public void join(long) 
    - public void join(long, int)
  + open.commonc.current.ConcurrentWorker
    - protected void doneJob()
    - public boolean remainsWorkJob()
     
[2019/09/03]
- Add
  + open.commons.utils.ObjectUtils.checkType(Class<?>, Class<?>)
  + open.commons.utils.ObjectUtils.load(Class<T>, Map<String, Object>)

[2019/08/29]
- Release: 1.6.15
- Tag: 1.6.16-SNAPSHOT
- Update
  + open.commons.utils.IOUtils.readFully(InputStream, int, boolean)
  + open.commons.utils.IOUtils.readStream(InputStream, int, boolean)
 
[2019/08/29]
- Tag: 1.6.15-SNAPSHOT
- Update
  + open.commons.test.StopWatch.toString()
  + open.commons.test.StopWatch.Record.toString()
 
[2019/08/27]
- Release: 1.6.14
- Bugfix
  + open.commons.utils.LangUtils.toNumber(String)


[2019/08/08]
- Add
	+ open.commons.utils.CollectionUtils
	  - toMapHSV(Collection<V>, BiFunction<V, Integer, K>, BiFunction<V, Integer, E>)
	  - toMapHSV(Collection<V>, BiFunction<V, Integer, K>, BiFunction<V, Integer, E>, Class<M>)
	  - toMapHSV(Collection<V>, BiFunction<V, Integer, K>, Function<V, E>)
	  - toMapHSV(Collection<V>, BiFunction<V, Integer, K>, Function<V, E>, Class<M>)
	  - toMapHSV(Collection<V>, Function<V, K>, BiFunction<V, Integer, E>)
	  - toMapHSV(Collection<V>, Function<V, K>, BiFunction<V, Integer, E>, Class<M>)
	+ open.commons.utils.FileUtils
		- clearDirectory(File)
		- learDirectory(String)

[2019/08/07]
- Release: 1.6.13
- Add
	+ open.commons.utils.IOUtils.transfer(Reader, Writer)
- Bugfix
	+ open.commons.utils.IOUtils.transfer(Reader, boolean, Writer, boolean)
		- 읽기버퍼 dummy 데이터 flush 처리

[2019/07/31]
- Release: 1.6.12
- Bugfix
  + open.commons.utils.StringUtils.rtrim(String)
  
[2019/07/22]
- Snapshot: 1.6.12-SNAPSHOT
- Add
  + open.commons.utils.FileUtils.removableFiles()
- Deprecated
  + open.commons.utils.LangUtils.removableFiles()

[2019/07/11]
- Release: 1.6.11
- Update
	+ open.commons.utils
		- ObjectUtils.transform(S, Class<D>)
- Add
	+ open.commons.utils
		- ObjectUtils.transform(S, boolean, Class<D>)
		- ObjectUtils.transform(S, boolean, Class<D>, boolean)
		- ObjectUtils.transform(S, Class<D>, boolean)		
	
[2019/07/04]
- Release: 1.6.10
- Add
	+ open.commons.utils
		- ArrayUtils.add(boolean[], boolean...)
		- ArrayUtils.add(byte[], byte...)
		- ArrayUtils.add(char[], char...)
		- ArrayUtils.add(double[], double...)
		- ArrayUtils.add(flot[], float...)
		- ArrayUtils.add(int[], int...)
		- ArrayUtils.add(long[], long...)
		- ArrayUtils.add(short[], short...)
		- ArrayUtils.add(T[], T...)
		- ArrayUtils.addAll(C, E...)
		- ArrayUtils.addIfAbsent(byte[], byte...)
		- ArrayUtils.addIfAbsent(char[], char...)
		- ArrayUtils.addIfAbsent(double[], double...)
		- ArrayUtils.addIfAbsent(float[], float...)
		- ArrayUtils.addIfAbsent(int[], int...)
		- ArrayUtils.addIfAbsent(long[], long...)
		- ArrayUtils.addIfAbsent(short[], short...)		
		- ArrayUtils.newList(List<T>, T...)
		- ArrayUtils.newList(T, Collection<T>)
		- ArrayUtils.newList(T, List<T>)
		- ArrayUtils.newSet(Set<T>, T...)
		- ArrayUtils.newSet(T, Collection<T>)
		- ArrayUtils.newSet(T, Set<T>)
		- ArrayUtils.newVector(T, Collection<T>)
		- ArrayUtils.newVector(T, Vector<T>)
		- ArrayUtils.newVector(Vector<T>, T...)
		- ArrayUtils.toPrimitiveArray(Boolean[])
		- ArrayUtils.toPrimitiveArray(byte[])
		- ArrayUtils.toPrimitiveArray(Character[])
		- ArrayUtils.toPrimitiveArray(Double[])
		- ArrayUtils.toPrimitiveArray(Float[])
		- ArrayUtils.toPrimitiveArray(Integer[])
		- ArrayUtils.toPrimitiveArray(Long[])		
		- ArrayUtils.toPrimitiveArray(Short[])

[2019/06/28]
- Add
	+ open.commons.util.IterableEnumeration
	+ open.commons.utils.LangUtils
	+ open.commons.utils.StringUtils
		- containsWhitespace(String)

[2019/06/21]
- Release: 1.6.9
- Add
  + open.commons.utils.ObjectUtils.transform(S, Class<D>)
  + open.commons.utils.StringUtils
    - concatenate(String, boolean, Collection<T>)
    - concatenate(String, boolean, Collection<T>, Function<T, R>)
    - concatenate(String, boolean, Map<K, V>, Function<Entry<K, V>, R>)
    - concatenate(String, boolean, Object...)
    - concatenate(String, boolean, String...)
    - concatenate(String, Collection<T>, Function<T, R>)
    - concatenate(String, Map<K, V>, Function<Entry<K, V>, R>)    

[2019/06/20]
- Release: 1.6.8.1
- Bugfix
	+ open.commons.utils.SQLUtils.invoke(ResultSet, ColumnDef, Method, Object)
- Add
	+ open.commons.annotation.Getter + open.commons.annotation.Setter
  
[2019/06/17]
- Release: 1.6.8
- Add
  + open.commons.utils.ReflectionUtils.getAnnotatedMethods(Class<A>, Class<?>)
  + open.commons.utils.SQLUtils.findDifferences(T, T, String...)

[2019/06/13]
- Release: 1.6.7.1
- Bugfix
  + open.commons.utils.StringUtils.indexOf(String, char, int): ordinal(세번째 파라미터) 적용 버그 수정
  
[2019/06/10]
- Release: 1.6.7
- Bugfix
  + open.commons.utils.SQLUtils.invoke(ResultSet, ColumnDef, Method, Object): 타입 비교 우선순위 버그 처리

[2019/04/12]
- Update
  + open.commons.util.BinarySortedList.add(E)
- Add
  + open.commons.util.BinarySortedList
    - insertelementAt(E, int)
    - grow(int)
    - hugeCapacity(int)

[2019/03/28]
- Add
  + open.commons.function.SQLFunction

[2019/03/20]
- Release: 1.6.5
- Add
  + open.commons.utils.IOUtils
    - readFully(InputStream, boolean)
    - readFully(InputStream, int, boolean)
- Deprecate
  + open.commons.utils.IOUtils.readFully(InputStream)

[2019/03/07]
- Release: 1.6.4
- Bugfix
  + open.commons.concurrent.ConcurrentWorker.get() 로직 수정

[2019/02/29]
- Update
  + open.commons.Result.setData(T)
  + open.commons.Result.setMessage(String)
  + open.commons.Result.setResult(boolean)

[2019/02/20]
- Release: 1.6.3
- Add
  + open.commons.test.StopWatch
  + open.commons.concurernt.ConcurrentJobManager
  + open.commons.concurrent.IJobFinishedListener

[2019/02/19]
- Release: 1.6.3
- Add
  + open.commons.database.IConnectionCallbackBroker
  + open.commons.database.ConnectionCallbackBroker2
  + open.commons.function.SQLBiConsumer
  + open.commons.function.SQLConsumer
  + open.commons.lang.AbstractCloseable
- Update
  + open.commons.database.AbstractDao

[2019/02/18]
- Release: 1.6.3
- Add
  + open.commons.database.AbstractDao

[2019/01/30]
- Release: 1.6.2
- BUGFIX
  + open.commons.utils.SQLUtils

[2019/01/29]
- Release: 1.6.1
- Add
  + open.commons.utils.JdbcUtils
  + open.commons.xml package

[2019/01/22]  (misspelled [2019/11/22])
- Release: 1.6.0
- Add
  + open.commons.util.BinarySortedList
- Update
  + open.commons.utils.CollectionUtils
  + open.commons.utils.MathUtils
  + open.commons.utils.OrderingUtils
  + open.commons.utils.TimeUtils

[2018/11/20]
- Release: 1.5.3
- Add
  + open.commons.utils.EncryptUtils

[2018/11/15]
- Release: 1.5.2.1
- Update
  + open.commons.utils.TimeUtils: Locale 지원

[2018/10/01]
- Update
  + open.commons.test.ElapsedTime 상태 체크 추가

[2018/09/28]
- Release: 1.5.2
- Add
  + open.commons.io.Consumers
  + open.commons.test.ElapsedTime
  + open.commons.utils.TimeUtils
- Update
  + open.commons.utils.StringUtils.toRegExString(String)

[2018/09/12]
- Release: 1.5.1
- Add
  + open.commons.utils.IOUtils
    - transfer(InputStream, Charset, boolean, Writer, boolean)
    - transfer(InputStream, Charset, Writer)
    - transfer(InputStream, Charset, Writer, boolean)
    - transfer(InputStream, String, boolean, Writer, String, boolean)
    - transfer(InputStream, String, Writer)
    - transfer(InputStream, String, Writer, boolean)
    - transfer(Reader, boolean, OutputStream, Charset, boolean)
    - transfer(Reader, boolean, Writer, boolean)
    - transfer(Reader, OutputStream, Charset)
    - transfer(Reader, OutputStream, Charset, boolean)
    - transfer(Reader, OutputStream, String)
    - transfer(Reader, OutputStream, String, boolean)
    - transfer(Reader, String, boolean, OutputStream, String, boolean)

[2018/09/12]
- Release: 1.5.0
- Update
  + open.commons.utils.CollectionUtils
    - toList(Stream\<E>, Function<E, NE>)
    - toList(Stream\<E>, Function<E, NE>, Class\<L>)
    - toSet(Stream\<E>, Function<E, NE>)
    - toSet(Stream\<E>, Function<E, NE>, Class\<S>)

[2018/09/10]
- Release: 1.4.17
- Update
  + open.commons.utils.IOUtils.transfer(InputStream, boolean, OutputStream, boolean)
    - transfer(InputStream, OutputStream, boolean)
    - transfer(InputStream, Charset, OutputStream, Charset)
    - transfer(InputStream, OutputStream, Charset)
    - transfer(InputStream, String, boolean, OutputStream, String, boolean)
    - transfer(InputStream, String, OutputStream, String)
- Add
  + open.commons.io.Closeables

[2018/06/18]
- Bug Fix
  + open.commons.concurrent.ConcurrentWorker.get()

[2018/05/30]
- Update
  + open.commons.utils.SQLUtils.newInstance(Class, ResultSet, String ...)
    - 데이터 타입에 맞는 함수 호출 적용

[2018/05/29]
- Add
  + open.commons.concurrent.ConcurrentWorker\<E>

[2018/04/18]
- Add
  + public static <T> T[] toArray(Collection<T>, Class<T>)

[2018/03/29]
- Release: 1.4.15
- Add
  + public interface open.commons.function.QuadFunction<T, U, V, W, R>
  + public interface open.commons.function.PentagonFunction<T, U, V, W, X, R>

[2018/02/08]
- Release: 1.4.14
- Add
  + <K, V, N, M extends Map<K, List<N>>> M open.commons.utils.CollectionUtils.toMap(Collection<V> ,Function<V, K>, BiFunction<K, V, N>, Class\<M>)
  + <K, V, N, M extends Map<K, List<N>>> M open.commons.utils.CollectionUtils.toMap(Collection<V> ,Function<V, K> ,BiFunction<K, V, N>)


[2018/01/31]
- Release: 1.4.13
- Add
  + open.commons.utils.ObjectUtils

[2018/01/05]
- Release: 1.4.12
- Update
  + open.commons.annotation.ColumnDef.caseSensitivie() 기본값 변경
    - false -> true

[2017/12/29]
- Release: 1.4.11
- Add
  + class open.commons.database.ConnectionCallbackBroker
  + class open.commons.database.IConnectionCallbackSetter
  + class open.commons.database.IRowMapperSetter

[2017/12/13]
- Release: 1.4.10
- Add
  + <C extends Collectin\<E>, E> C open.commons.utils.CollectionUtils.addIfNotNull(C, Class, E)
  + <C extends Collectin\<E>, E> C open.commons.utils.CollectionUtils.addAllIfNotNull(C, Class, E[])

[2017/12/04]
- Release: 1.4.9
- Add
  + open.commons.utils.MathUtils

[2017/10/18]
- Release: 1.4.8.1
- Add
  + open.commons.utils.CollectionUtils.read(List<T>, int, int)
  + open.commons.utils.CollectionUtils.readAsArray(List<T>, int, int, Class)
  + open.commons.utils.MapUtils.read(Map<K, V>, int)

[2017/09/22]
- Release: 1.4.8
- Add
  + open.commons.function.SQLBiFunction
  + open.commons.function.TripleConsumer
  + open.commons.function.TripleFunction

[2017/09/22]
- Release: 1.4.7
- Add
  + open.commons.annotation.ColumnDef
  + open.commons.utils.SQLUtils

[2017/09/07]
- Release: 1.4.6
- Add
  + open.commons.concurrent.DefaultThreadFactory

[2017/09/06]
- Release: 1.4.5
- Update
  + <K, V> Map<K,V> open.commons.utils.CollectionUtils.toMap<Enumeration<V>, Function<V, K>);
  + <K, V, M extends Map<K, V>> M  open.commons.utils.CollectionUtils.toMap<Enumeration<V>, Function<V, K>, Class\<M>);
  + <K, V> Map<K,V> open.commons.utils.CollectionUtils.toMap<Enumeration<V>, IKeyExtractor<K, V>);
  + <K, V, M extends Map<K, V>> M  open.commons.utils.CollectionUtils.toMap<Enumeration<V>, IKeyExtractor<K, V>, Class\<M>);

[2017/09/06]
- Release: 1.4.4.0
- Update
  + boolean open.commons.utils.AssertUtils.assert0(Class, String) 버그 패치
  + int open.commons.utils.IOUtils.readStream(InputStream, int, boolean) 추가
  + int open.commons.utils.IOUtils.readStream(InputStream, int) 수정

[2017/08/07]
- Release: 1.4.3.1
- Update
  + boolean open.commons.utils.StringUtils.isWhiteSpace(String) 버그 패치

[2017/07/27]
- Release: 1.4.3
- Update
  + open.commons.utils.CollectionUtils.java

[2017/07/06]
- Release: 1.4.2.1
- Update
  + open.commons.utils.DateUtil.java
  + open.commons.utils.StringUtils.java

[2017/03/14]
- Release: 1.4.2
- Update
  + open.commons.utils.DateUtil.java
  + open.commons.utils.StringUtils.java

[2016/09/20]
- Release: 1.4.1
- Update
  + open.commons.utils.StringUtils.split(String, String, boolean, int)
  + open.commons.utils.StringUtils.splitWithoutBracket(String, String, boolean)
  + open.commons.utils.StringUtils.splitWithoutBracket(String, String, boolean, int)

[2016/03/15]
- Release: 1.4.0
- Update
  + open.commons.utils.ReflectionUtils.getAllAnnodatedFields(Object, Class, IFilter)
  + open.commons.utils.ReflectionUtils.getAllAnnodatedFields(OBject, Class)

[2015/12/11]
- Release: 1.3.3
- Update
  + open.commons.utils.CollectionUtils.getIgnoreCase(): Apply generic

[2015/07/23]
- Release: 1.3.2
- Bugfix
  + open.commons.utils.FileUtils.getFileExtension(File)
  + open.commons.io.FileRecursiveHandler: add statistics APIs.

[2015/04/14]
- Release: 1.3.1
- Add
  + open.commons.net.SubNetwork

[2015/03/17]
- Release: 1.3.0
- Bugfix
  + open.commons.utils.NetUtils.REGEX_IPV4 값 수정

[2015/03/14]
- Release: 1.2.8
- Bugfix
  + public String FIFOMap.toString(): Infinite Loop 버그 수정

[2015/03/12]
- Release: 1.2.6
- Bugfix
  + public static byte[] readStream(InputStream inStream, final int length)  버그 수정

[2015/03/08]
- Release: 1.2.4
- Add
  + open.commons.utils.NetUtils

[2015/01/13]
- Release: 1.2.2
- Add
  + open.commons.utils.ExceptionUtils

[2014/12/02]
- Release 1.2.1
- Add
  + open.commons.text.NamedTemplate.clear();


[2014/11/07]
- Release 1.2.0 <- 1.1.3
- Add
  + open.comons.utils.AnnotationUtils.getAnnotatedFields(Object, Class<T>)
  + open.comons.utils.AnnotationUtils.getAnnotatedMethods(Object, Class<T>)
[2014/10/17]
- Release 1.1.3
- Add
  + open.commons.util.CollectionUtils.toList(Collection)
  + open.commons.util.CollectionUtils.toList(Collection, Class)
  + open.commons.util.CollectionUtils.toSet(Collection)
  + open.commons.util.CollectionUtils.toSet(Collection, Class)
  + open.commons.collections.FIFOMap.atFirst()

[2014/09/26]
- Release 1.1.2
- Add
  + open.commons.annotation.Hide
  + open.commons.reflect.IAccessibleObjectAction
  + open.commons.utils.DataUtils

[2014/09/17]
- Release 1.1.1
- Change
  + open.commons.validation.supports -> open.commons.validation.token

[2014/09/05]
- Release 1.1.0
- Bug fix
  + open.commons.text.NamedTemplate
    - public static String format(String pattern, Map<String, Object> values, boolean trim): name에 대한 trim() 처리 버그

[2014/07/10]
- Add
  + open.commons.utils.IntegerUtils.parseInt(String)
  + open.commons.utils.IntegerUtils.radix)String)

- Change
  + open.commons.utils.IntegerUtils.parseInt(String, int)
  + open.commons.utils.IntegerUtils.parseInt(String, int, int)

[2014/06/30]
- Add
  + open.commons.utils.StringUtils.toStrings(String ...)

[2014/06/24]
- Bug fix
  + AbstractRunnable.stop() 'NullPointerException'

[2014/06/18]
- Release 1.0.9
- New
  + open.commons.reflect.IllegalGenericNameException 추가
  + open.commons.reflect.TypeVariableNamses 추가

- Update
  + open.commons.utils/ArrayUtils.java
    - public static Class<?>[] adjustByLength(int length, Class<?>... classes)

[2014/06/04]
- Release 1.0.8
- Changes !!!!!!!!!!!!!
  + open.commons.reflect.GenericParamType -> open.commons.reflect.GenericTypeVariable

[2014/06/03]
- New!!!
  + open.commons.utils.DateUtil2

[2014/05/30]
- Hot !!!
  + License is changed!!!
    - before: COPYLEFT by 'Open Commons' &  Park Jun-Hong All Rights Reserved when use for commercial purpose.
    - after:

- Changes
  + open.commons.text.NamedFormat -> open.commons.text.NamedTemplate

- New!!!
  + open.commons.net.EtherTypejava

[2014/05/27]
- New!!!
  + open.commons.net.Protocols.java

[2014/05/08]
- Release 1.0.7
  + open.commons.lang.ToStringHashCode 추가

[2014/05/02]
- Release 1.0.6

  + New interface: open.commons.util.IFilter<T>
  + Modify wrong words.
   - candidator -> candidate

[2014/04/23]
- Release 1.0.5
- New
  + open.commons.text.NamedFormat 추가
  + open.commons.utils.Updated ByteUtils.hexBinStringToByteArray()
