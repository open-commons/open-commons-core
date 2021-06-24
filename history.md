[2021/06/24]
- Add
  + open.commons.core.utils.ArrayUtils
    - splitAfter(T[], Function&lt;T, Boolean&gt;)

[2021/06/21]
- Add
  + open.commons.core.utils.ArrayUtils
    - indexOf(boolean[], Function<Boolean, Boolean>)
    - indexOf(byte[], Function<Byte, Boolean>)
    - indexOf(char[], Function<Character, Boolean>)
    - indexOf(double[], Function<Double, Boolean>)
    - indexOf(float[], Function<Float, Boolean>)
    - indexOf(int[], Function<Integer, Boolean>)
    - indexOf(long[], Function<Long, Boolean>)
    - indexOf(short[], Function<Short, Boolean>)
    - indexOf(T[], Function<T, Boolean>)
    - splitAt(T[], Function&lt;T, Boolean&gt;, Function&lt;T, T&gt;)
    - splitBefore(T[], Function&lt;T, Boolean&gt;)
  + open.commons.core.utils.StringUtils
    - startsWithDigit(String)

[2021/06/18]
- Add
  + open.commons.core.csv.AbstractCsvData
  + open.commons.core.csv.FieldBase
  + open.commons.core.csv.MethodBase
  + open.commons.core.lang.Char
- Modify
  + open.commons.core.database.IndexedColumnDTO
    - 공통 기능을 open.commons.core.csv.AbstractCsvData 로 이관

[2021/06/16]
- Add
  + open.commons.core.utils.MapUtils.map(Map&lt;K, V&gt;, Function&lt;Entry&lt;K, V&gt;, NK&gt;, Function&lt;Entry&lt;K, V&gt;, NV&gt;, Class&lt;M&gt;, Class&lt;C&gt;)

[2021/05/55]
- bugfix
  + open.commons.collection.FIFOMap.clear(): 데이터(값) 미삭제 버그 수정

[2021/02/26]
- Add
  + open.commons.function.IOFunction&lt;T, R&gt;

[2021/02/26]
- Bugfix
  _ open.commons.function.SQLConsumer
    + setParameters(Object, String...): 파라미터가 문자열만 오는 경우 판단 오류 수정. (Reported by. 'jhlee@ymtech.kr')

[2021/02/19]
- Add
  + open.commons.concurrent.ConcurrentWorker
    - getJobCount()

[2021/02/18]
- Add
  + open.commons.utils.DateUtil
    - resetDateFields(int...)
  + open.commons.utils.FileUtils
    - move(Path, Path, CopyOption...)

[2021/02/10]
- Add
  + open.commons.utils.FileUtils
    - move(File, File, CopyOption...)
    - move(Path, String, CopyOption...)
    - move(String, Path, CopyOption...)
    - move(String, String, CopyOption...)
  
[2021/02/09]
- Modify
  + open.commons.concurrent.AsyncJobManager
    - get(K)
    - register(K, Future&lt;?&gt;)
    - unregister(K)
  + open.commons.concurrent.AsyncJobManager.Builder
    - getManager(Object)
  	
[2021/02/09]
- Add
  + open.commons.utils.FileUtils
    - listFiles(Path, int, BiFunction&lt;Path, BasicFileAttributes, Boolean&gt;)

[2021/01/14]
- Add
  + open.commons.utils.IOUtils
    - transfer(InputStream, boolean, OutputStream, boolean, int)
    - transfer(InputStream, Charset, boolean, Writer, boolean, int)
    - transfer(InputStream, Charset, OutputStream, Charset, int)
    - transfer(InputStream, Charset, Writer, boolean, int)
    - transfer(InputStream, Charset, Writer, int)
    - transfer(InputStream, OutputStream, boolean, int)
    - transfer(InputStream, OutputStream, Charset, boolean, int)
    - transfer(InputStream, OutputStream, Charset, int)
    - transfer(InputStream, OutputStream, int)
    - transfer(InputStream, OutputStream, String, boolean, int)
    - transfer(InputStream, OutputStream, String, int)
    - transfer(InputStream, String, boolean, OutputStream, String, boolean, int)
    - transfer(InputStream, String, boolean, Writer, boolean, int)
    - transfer(InputStream, String, OutputStream, String, int)
    - transfer(InputStream, String, Writer, boolean, int)
    - transfer(InputStream, String, Writer, int)
    - transfer(Reader, boolean, OutputStream, Charset, boolean, int)
    - transfer(Reader, boolean, OutputStream, String, boolean, int)
    - transfer(Reader, boolean, Writer, boolean, int)
    - transfer(Reader, boolean, Writer, boolean)
    - transfer(Reader, OutputStream, Charset, boolean, int)
    - transfer(Reader, OutputStream, Charset, int)
    - transfer(Reader, OutputStream, String, boolean, int)
    - transfer(Reader, OutputStream, String, int)
    - transfer(Reader, Writer, int)  

[2020/12/22]
- Add
  + open.commons.utils.ObjectUtils
    - isPrimitive(Class&lt;?&gt;)
    - isPrimitive(Object)
    - isWrapper(Class&lt;?&gt;)
    - isWrapper(Object)
  + open.commons.utils.SQLConsumer
    - setParameters(Object, String...)
  + open.commons.utils.SQLUtils
    - setParameters(PreparedStatement, int, Object, String...)
- Modify
  + open.commons.SQLTripleFunction
    - setParameters(String...):  내부 구현부가 open.commons.utils.SQLUtils.setParameters(PreparedStatement, int, Object, String...)로 이관됨.

[2020/12/17]
- New
  + open.commons.lang.NumString    
- Add
  + open.commons.utils.ByteUtils
    - hexBinString(boolean, byte ...)
    - hexBinString(String, boolean, byte...)
    - hexBinString(String, byte...)
    - toIPv4Expr(byte[])
    - toMACExpr(byte[])
  + open.commons.utils.IntegerUtils
    - hex(String)
    - toIPv4(int)
    - toIPv4(int[])
- Delete
  + open.commons.utils.ByteUtils
    - main(String[])

[2020/12/15]
- Add
  + open.commons.Utils.CollectionUtils
    - newList(boolean...)
    - newList(byte...)
    - newList(char...)
    - newList(double...)
    - newList(int...)
    - newList(long...)
    - newList(short...)
    - newSet(boolean...)
    - newSet(byte...)
    - newSet(char...)
    - newSet(double...)
    - newSet(int...)
    - newSet(long...)
    - newSet(short...)
    - newVector(boolean...)
    - newVector(byte...)
    - newVector(char...)
    - newVector(double...)
    - newVector(int...)
    - newVector(long...)
    - newVector(short...)
  
[2020/12/13]
- Add
  + open.commons.Utils.CollectionUtils
    - addAllIfNotNull(C, Class&lt;? extends C&gt;, Collection&lt;E&gt;)
    - addIfAbsent(List&lt;T&gt;, T)
    
[2020/12/08]
- Add
  + open.commons.utils.ObjectUtils
    - transform(S, boolean, D, boolean)
    - transform(S, boolean, D)
    - transform(S, D, boolean)
    - transform(S, D)

[2020/11/20]
- Add
  + open.commons.Result
    - copyOf(Result&lt;?&gt;)
    
[2020/11/16]
- Add
  + open.commons.io.IRandomAccessible
  + open.commons.io.TextLineInfo
- Updated
  + open.commons.utils.ArrayItr
    - Interable&lt;E&gt; 인터페이스 상속 추가
    - public Iterator&lt;E&gt; iterator() 추가
    - public void reset() 추가
  + open.commons.utils.IOUtils
    - readChannel(FileChannel, Function&lt;byte[], T&gt;, Iterable&lt;R&gt;)
    - readChannel(FileChannel, int, ByteBuffer, Function&lt;byte[], T&gt;)
    - readChannel(FileChannel, int, Function&lt;byte[], T&gt;, R...)
    - readFile(File, Function&lt;byte[], T&gt;, Iterable&lt;R&gt;)
    - readFile(File, Function&lt;byte[], T&gt;, R...)
    - readFile(File, Iterable&lt;R&gt;)
    - readFile(File, R)
    - readFile(File, R...)
    - readFile(RandomAccessFile, Function&lt;byte[], T&gt;, Iterable&lt;R&gt;)
    - readFile(RandomAccessFile, Function&lt;byte[], T&gt;, R)
    - readFile(RandomAccessFile, Function&lt;byte[], T&gt;, R...)
    - readFile(RandomAccessFile, R)
    - readFile(String, Function&lt;byte[], T&gt;, Iterable&lt;R&gt;)
    - readFile(String, Function&lt;byte[], T&gt;, R...)
    - readFile(String, Iterable&lt;R&gt;)
    - readFile(String, R)
    - readFile(String, R...)
    - readFully(InputStream)
    - readFully(InputStream, boolean)
    - readFully(InputStream, int, boolean)
    - readFully(ReadableByteChannel, int, boolean)  

[2020/11/11]
- Add
 + open.commons.concurrent.AsyncJobManager: 비동기 작업을 처리할 수 있도록 관리하는 클래스.
 + oepn.commons.database.IndexedColumnDTO: DB Table Column에 대응하는 데이터를 정해진 순서에 맞도록 CSV 데이터를 생성하도록 지원
 + open.commons.database.annotation.ColumnConstraints : DB Table Column 에 정의된 Constraint 를 DTO 레벨에서 확인할 수 있도록 명시

[2020/10/29]
- Snapshot: 1.8.0-SNAPSHOT
- Add
  + open.commons.database.ConnectionCallbackBroker2
    - getStatement(Connection)
    - ConnectionCallbackBroker2(String, T, boolean)
  + open.commons.database.DefaultConCallbackBroker2
    - DefaultConCallbackBroker2(String, SQLConsumer&lt;PreparedStatement&gt;, boolean)
  + open.commons.database.IConnectionCallbackBroker
    - getStatement(Connection)

[2020/10/29]
- Release: 1.7.0

[2020/10/28]
- Add
  + open.commons.utils.ExceptionUtils
    - startsWith(String, String, boolean)
    - startsWith(Throwable, String)
    - startsWith(Throwable, String, int)
    - startsWithIgnoreCase(Throwable, String)
    - startsWithIgnoreCase(Throwable, String, int)
 
[2020/10/21]
- Bugfix
  + open.commons.xml.SaxTextConverter
    - convert(Object, String, String): 문자열이 모두 whitespace 로 이루어진 경우 메소드가 없으면 처리

[2020/10/15]
- Add
  + open.commons.utils.ExceptionUtils
    - newException(Class&lt;E&gt;, String, Object...)

[2020/09/25]
- Update
  + open.commons.utils.IOUtils
    - getReader(File, Charset)
    - getReader(File, String)  
    - getReader(Path)
    - getReader(Path, Charset)
    - getReader(Path, String)
    - getReader(InputStream, Charset)
  + open.commons.utils.StringUtisl
    - exists(String, Collection&lt;String&gt;)
    - exists(String, String...)
    - existsIgnoreCase(String, Collection&lt;String&gt;)
    - existsIgnoreCase(String, String...)
  
[2020/09/24]
- Update
  + open.commons.annotation.ColumnValue  
    - ColumnNameType columnNameType() 추가
  + open.commons.function.SQLTripleFunction
    - ColumnNameType 처리 추가
    
[2020/09/13]
- Update
 + open.commons.utils.IOUtils
   - byte[] readFully(InputStream,int, boolean)
- Add
 + open.commons.utils.IOUtils
   - byte[] readFully(ReadableByteChannel, int, boolean) {

[2020/09/10]
- Add
  + open.commons.utils.DateUtil
    - Calendar getCalendar(int, int)
    - Date getDate(int, int)
    - long getTimestamp(int, int)

[2020/09/02]
- Add
 + open.commons.utils.MathUtils
   - &lt;T extends Comparable&lt;T&gt;&gt; T max(T ...)
   - &lt;T extends Comparable&lt;T&gt;&gt; T min(T ...)

[2020/08/29]
- Add
  + open.commons.functin.Runner
  + open.commons.utils.FunctionUtils
- Deprecated
  + open.commons.utils.StreamUtils
  
[2020/08/17]
- Add
  + open.commons.concurrent.ConcurrentWorker
    - Collection&lt;E&gt; flush()
    - E get(boolean)
    - void push(Collection&lt;E&gt;)

[2020/08/13]
- Update
  + open.commons.function.SQLConsumer
    - setParameters(Object...): Variable Binding 이후 파라미터 자원 해제

[2020/7/29]
- Bugfix
  + open.commons.utils.DateUtil.REGEX_yyyyMMDD_HHmmss: 정규식 오류 수정
  + open.commons.utils.DateUtil2.REGEX_yyyyMMDD_HHmmss: 정규식 오류 수정
- Update
  + open.commons.utils.StreamUtils
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T, R&gt;, Supplier&lt;R&gt;)
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T,U&gt;, Function&lt;U, R&gt;, Supplier&lt;R&gt;)
    - runIf(T, Predicate&lt;T&gt;, Supplier&lt;T,U&gt;, Function&lt;U, R&gt;, Supplier&lt;R&gt;)
- Update
  + open.commons.utils.StreamUtils
    - build(BiFunction&lt;S, T, U&gt;, S, T, BiFunction&lt;V, W, X&gt;, V, Function&lt;U, W&gt;, Function&lt;Throwable, X&gt;)
    - build(BiFunction&lt;T, U, R&gt;, T, U, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;)
- Delete
  + open.commons.utils.StreamUtils    
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T, R&gt;, R)
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T,U&gt;, Function&lt;U, R&gt;, R)
    - runIf(T, Predicate&lt;T&gt;, Suplier&lt;T,U&gt;, Function&lt;U, R&gt;, R)
    - runIf(T, Predicate&lt;T&gt;, Suplier&lt;T,U&gt;, Function&lt;U, R&gt;)

[2020/06/14]
- Add
  + open.commons.utils.StreamUtils
    - build(BiFunction&lt;S, T, U&gt;, S, T, BiFunction&lt;V, W, X&gt;, V, Function&lt;U, W&gt;, Function&lt;Throwable, X&gt;)
    - build(BiFunction&lt;T, U, R&gt;, T, U, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;)
    - build(Function&lt;S, T&gt;, S, Function&lt;U, X&gt;, Function&lt;T, U&gt;, Function&lt;Throwable, X&gt;)
    - build(Function&lt;T, R&gt;, T, Function&lt;R, X&gt;, Function&lt;Throwable, X&gt;)
    - build(Function&lt;T, Result&lt;R&gt;&gt;, T, Consumer&lt;R&gt;)
    - build(Function&lt;T, Result&lt;R&gt;&gt;, T, Consumer&lt;R&gt;, Function&lt;Throwable, String&gt;)
    - getOnAsync(Future&lt;Result&lt;R&gt;&gt;)
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T, R&gt;)
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T, R&gt;, R)
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T, U&gt;, Function&lt;U, R&gt;)
    - runIf(T, Predicate&lt;T&gt;, Function&lt;T, U&gt;, Function&lt;U, R&gt;, R)
    - runIf(T, Predicate&lt;T&gt;, Supplier&lt;U&gt;, Function&lt;U, R&gt;)
    - runIf(T, Predicate&lt;T&gt;, Supplier&lt;U&gt;, Function&lt;U, R&gt;, R)

[2020/05/27]
- Release: 1.6.18
- Bug Fix
  + open.commons.SQLUtils.invoke(ResultSet, ColumnDef, Method, Object)
    - primitive type 의 Wrapper Type인 경우 DB조회결과가 null 인 경우 primitive type의 기본값 설정 버그 수정

[2020/05/20]
- Update
  + .gitignore
  + POM.xml
    - Apply Javadoc

[2020/04/10]
- Add
  + open.commons.utils.StreamUtils

[2020/03/29]
- Add
  + open.commons.utils.MapUtils.getOrDefault(Map&lt;K, V&gt;, K, V, boolean)
  
[2020/02/14]
- Add
  + open.commons.Result
    - setMessage(String, Object...)
    - error(String)
    - success(T)
  
[2020/02/13]
- Tag: 1.6.18-SNAPSHOT

[2020/02/13]
- Release: 1.6.17
- Add
  + open.commons.function.HexaFunction
- Update
  + open.commons.annotation.Getter
  + open.commons.function.SQLTripleFunction
  + open.commons.utils.CollectionUtils
  + open.commons.utils.IOUtils
  + open.commons.utils.SQLUtils

[2020/01/22]
- Add
  + open.commons.function
    - SQLTripleConsumer
    - SQLTripleFunction
  + open.commons.annotations.ColumnValue
  	
- Update
  + open.commons.annotations.ColumnDef
    - (+) open.commons.annotation.ColumnDef.ColumnNameType
    - (+) ColumnNameType open.commons.annotation.ColumnDef.columnNameType() : ColumnNameType.NAME
  + open.commons.function
    - SQLConsumer
      - (+) SQLConsumer&lt;PreparedStatement&gt; open.commons.function.SQLConsumer.setParameters(Object[])
    - TripleFunction
  + open.commons.utils
    - StringUtils
      - String concat(List&lt;String&gt;, String, boolean, boolean, boolean)
      - String toKebabCase(String)
      - String toPascalCase(String)
      - String toSnakeCase(String)
- Change
  + open.commons.annotations.ColumnDecl
    - FROM 'OLD' open.commons.annotations.ColumnValue  
    
- Patch
  + open.commons.utils.SQLUtils
    - void invoke(ResultSet, ColumnDef, Method, Object): ColumnDef.columnNameType() 처리 추가

- Bugfix
  + open.commons.collection.FIFOSet
    - boolean open.commons.collection.FIFOSet.remove(Object o): 데이터 포함 여부 버그


[2019/12/20]
- Release: 1.6.16
- Omit "-RELEASE"

[2019/11/28]
- Tag: 1.6.17-SNAPSHOT
- Update
  + open.commons.lang.DefaultRunnable
    - implements AutoCloseable  

[2019/11/28]
- Release: 1.6.16-RELEASE
- Add
  + open.commons.utils.CollectionUtils
    - toMap(Collection&lt;V&gt;, Function&lt;V, K&gt;, Function&lt;V, E&gt;, Class&lt;M&gt;, Class&lt;C&gt;)
    
[2019/11/26]
- Add
  + open.commons.database.AbstractOracleInsertDao
- Update
  + open.commons.database.AbstractInsertDao
  

[2019/10/24]
- Add
  + open.commons.util.ArrayItr&lt;E&gt;


[2019/10/17]
- Update
  + open.commons.lang.DefaultRunnable
    - beforeRun()
    - run() 메소드 final로 정의하고 Process ID를 ThreadContext에 'pid' 라는 이름으로 추가하는 기능 구현.
    - runInternal(): 실제 기능 구현 메소드
  + open.commons.concurrent.ConcurrentJobManager&lt;E&gt;
    - void run()에 구현된 내용을 void runInternal()로 이관
  + open.commons.concurrent.ConcurrentWorker&lt;E&gt;
    - void run() -> void runInternal()로 대체      
  + org.apache.logging.log4j.Logger -> org.slf4j.Logger 로 교체
    - open.commons.database.AbstractDao
    - open.commons.lang.AbstractCloseable
    - open.commons.xml.AbstractSAXHandler


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
  + open.commons.utils.ObjectUtils.checkType(Class&lt;?&gt;, Class&lt;?&gt;)
  + open.commons.utils.ObjectUtils.load(Class&lt;T&gt;, Map&lt;String, Object&gt;)

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
	  - toMapHSV(Collection&lt;V&gt;, BiFunction&lt;V, Integer, K&gt;, BiFunction&lt;V, Integer, E&gt;)
	  - toMapHSV(Collection&lt;V&gt;, BiFunction&lt;V, Integer, K&gt;, BiFunction&lt;V, Integer, E&gt;, Class&lt;M&gt;)
	  - toMapHSV(Collection&lt;V&gt;, BiFunction&lt;V, Integer, K&gt;, Function&lt;V, E&gt;)
	  - toMapHSV(Collection&lt;V&gt;, BiFunction&lt;V, Integer, K&gt;, Function&lt;V, E&gt;, Class&lt;M&gt;)
	  - toMapHSV(Collection&lt;V&gt;, Function&lt;V, K&gt;, BiFunction&lt;V, Integer, E&gt;)
	  - toMapHSV(Collection&lt;V&gt;, Function&lt;V, K&gt;, BiFunction&lt;V, Integer, E&gt;, Class&lt;M&gt;)
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
		- ObjectUtils.transform(S, Class&lt;D&gt;)
- Add
	+ open.commons.utils
		- ObjectUtils.transform(S, boolean, Class&lt;D&gt;)
		- ObjectUtils.transform(S, boolean, Class&lt;D&gt;, boolean)
		- ObjectUtils.transform(S, Class&lt;D&gt;, boolean)		
	
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
		- ArrayUtils.newList(List&lt;T&gt;, T...)
		- ArrayUtils.newList(T, Collection&lt;T&gt;)
		- ArrayUtils.newList(T, List&lt;T&gt;)
		- ArrayUtils.newSet(Set&lt;T&gt;, T...)
		- ArrayUtils.newSet(T, Collection&lt;T&gt;)
		- ArrayUtils.newSet(T, Set&lt;T&gt;)
		- ArrayUtils.newVector(T, Collection&lt;T&gt;)
		- ArrayUtils.newVector(T, Vector&lt;T&gt;)
		- ArrayUtils.newVector(Vector&lt;T&gt;, T...)
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
  + open.commons.utils.ObjectUtils.transform(S, Class&lt;D&gt;)
  + open.commons.utils.StringUtils
    - concatenate(String, boolean, Collection&lt;T&gt;)
    - concatenate(String, boolean, Collection&lt;T&gt;, Function&lt;T, R&gt;)
    - concatenate(String, boolean, Map&lt;K, V&gt;, Function&lt;Entry&lt;K, V&gt;, R&gt;)
    - concatenate(String, boolean, Object...)
    - concatenate(String, boolean, String...)
    - concatenate(String, Collection&lt;T&gt;, Function&lt;T, R&gt;)
    - concatenate(String, Map&lt;K, V&gt;, Function&lt;Entry&lt;K, V&gt;, R&gt;)    

[2019/06/20]
- Release: 1.6.8.1
- Bugfix
	+ open.commons.utils.SQLUtils.invoke(ResultSet, ColumnDef, Method, Object)
- Add
	+ open.commons.annotation.Getter + open.commons.annotation.Setter
  
[2019/06/17]
- Release: 1.6.8
- Add
  + open.commons.utils.ReflectionUtils.getAnnotatedMethods(Class&lt;A&gt;, Class&lt;?&gt;)
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
    - toList(Stream\&lt;E&gt;, Function&lt;E, NE&gt;)
    - toList(Stream\&lt;E&gt;, Function&lt;E, NE&gt;, Class\&lt;L&gt;)
    - toSet(Stream\&lt;E&gt;, Function&lt;E, NE&gt;)
    - toSet(Stream\&lt;E&gt;, Function&lt;E, NE&gt;, Class\&lt;S&gt;)

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
  + open.commons.concurrent.ConcurrentWorker\&lt;E&gt;

[2018/04/18]
- Add
  + public static &lt;T&gt; T[] toArray(Collection&lt;T&gt;, Class&lt;T&gt;)

[2018/03/29]
- Release: 1.4.15
- Add
  + public interface open.commons.function.QuadFunction&lt;T, U, V, W, R&gt;
  + public interface open.commons.function.PentagonFunction&lt;T, U, V, W, X, R&gt;

[2018/02/08]
- Release: 1.4.14
- Add
  + &lt;K, V, N, M extends Map&lt;K, List&lt;N&gt;&gt;&gt; M open.commons.utils.CollectionUtils.toMap(Collection&lt;V&gt; ,Function&lt;V, K&gt;, BiFunction&lt;K, V, N&gt;, Class\&lt;M&gt;)
  + &lt;K, V, N, M extends Map&lt;K, List&lt;N&gt;&gt;&gt; M open.commons.utils.CollectionUtils.toMap(Collection&lt;V&gt; ,Function&lt;V, K&gt; ,BiFunction&lt;K, V, N&gt;)


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
  + &lt;C extends Collectin\&lt;E&gt;, E&gt; C open.commons.utils.CollectionUtils.addIfNotNull(C, Class, E)
  + &lt;C extends Collectin\&lt;E&gt;, E&gt; C open.commons.utils.CollectionUtils.addAllIfNotNull(C, Class, E[])

[2017/12/04]
- Release: 1.4.9
- Add
  + open.commons.utils.MathUtils

[2017/10/18]
- Release: 1.4.8.1
- Add
  + open.commons.utils.CollectionUtils.read(List&lt;T&gt;, int, int)
  + open.commons.utils.CollectionUtils.readAsArray(List&lt;T&gt;, int, int, Class)
  + open.commons.utils.MapUtils.read(Map&lt;K, V&gt;, int)

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
  + &lt;K, V&gt; Map&lt;K,V&gt; open.commons.utils.CollectionUtils.toMap&lt;Enumeration&lt;V&gt;, Function&lt;V, K&gt;);
  + &lt;K, V, M extends Map&lt;K, V&gt;&gt; M  open.commons.utils.CollectionUtils.toMap&lt;Enumeration&lt;V&gt;, Function&lt;V, K&gt;, Class\&lt;M&gt;);
  + &lt;K, V&gt; Map&lt;K,V&gt; open.commons.utils.CollectionUtils.toMap&lt;Enumeration&lt;V&gt;, IKeyExtractor&lt;K, V&gt;);
  + &lt;K, V, M extends Map&lt;K, V&gt;&gt; M  open.commons.utils.CollectionUtils.toMap&lt;Enumeration&lt;V&gt;, IKeyExtractor&lt;K, V&gt;, Class\&lt;M&gt;);

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
  + open.comons.utils.AnnotationUtils.getAnnotatedFields(Object, Class&lt;T&gt;)
  + open.comons.utils.AnnotationUtils.getAnnotatedMethods(Object, Class&lt;T&gt;)
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
    - public static String format(String pattern, Map&lt;String, Object&gt; values, boolean trim): name에 대한 trim() 처리 버그

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
    - public static Class&lt;?&gt;[] adjustByLength(int length, Class&lt;?&gt;... classes)

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

  + New interface: open.commons.util.IFilter&lt;T&gt;
  + Modify wrong words.
   - candidator -> candidate

[2014/04/23]
- Release 1.0.5
- New
  + open.commons.text.NamedFormat 추가
  + open.commons.utils.Updated ByteUtils.hexBinStringToByteArray()
