## Câu 1 
Trình bày khái niệm liên quan đến các đối tượng sau:
UserNamePasswordAuthenticationToken
AuthenticationManager
AuthenticationProvider
PasswordEncoder
UserDetailsService
UserDetails

Trả lời:

UserNamePasswordAuthenticationToken Là 1 class của Spring security được sử dụng để tạo ra một Object Authentication sau khi người dùng **đã đăng nhập thành công(người dùng đã được xác thực)** trong ứng dụng web Spring.
```java
public class UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken {
    //...
}
public abstract class AbstractAuthenticationToken implements Authentication, CredentialsContainer {
    //...
}
```
Class Authentication chứa thông tin về người dùng được xác thực, bao gồm tên đăng nhập, mật khẩu và danh sách các quyền truy cập của người dùng.

Các thuộc tính quan trọng:
- principal: thông tin người dùng (được build như cấu trúc UserDetails hoặc được tùy chỉnh(customize) thành một Object tương đương)
- credentials: mật khẩu của người dùng 
- authorities: danh sách các quyền truy cập

AuthenticationManager là 1 interface triển khai 1 phương thức xác thực cho 1 Object Authentication:

AuthenticationManager không thực hiện xác thực độc lập mà sử dụng các Provider(AuthenticationProvider) để thực hiện xác thực cho từng loại nguồn xác thực. Mỗi Provider sẽ có nhiệm vụ xác thực cho một loại nguồn xác thực như LDAP, database hoặc xác thực tùy chỉnh(customize).
```java
public interface AuthenticationManager {
    Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
```
- Xác thực thành công trả về 1 Object Authentication đã được xác thực.
- Xác thực thất bại ném 1 ngoại lệ AuthenticationException thể hiện vì sao thất bại(không tồn tại username, password không đúng...).

AuthenticationProvider là 1 interface triển khai 1 phương thức xác thực cho 1 Object Authentication:
```java
public interface AuthenticationProvider {
    Authentication authenticate(Authentication authentication) throws AuthenticationException;

    boolean supports(Class<?> authentication);
}
```
Phương thức xác thực tương tự như AuthenticationManager ở trên nhưng căn cứ vào thuộc tính supports(Class<?> authentication) mà interface AuthenticationProvider xác định xem Provider này có hỗ trợ xác thực cho lớp đối tượng Authentication cụ thể nào không.

Các class liên quan đến thuộc tính này:
- DaoAuthenticationProvider: sử dụng xác thực thông tin đăng nhập từ database.
- LdapAuthenticationProvider: xác thực thông tin đăng nhập từ máy chủ LDAP.
- AnonymousAuthenticationProvider: xác thực các yêu cầu được gửi từ người dùng ẩn danh.

PasswordEncoder được sử dụng để mã hóa mật khẩu của người dùng khi đăng nhập.
```java
public interface PasswordEncoder {
    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);

    default boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
```
Trong quá trình xác thực khi người dùng gửi lên tên đăng nhập, mật khẩu, sau khi tìm được User với tên đăng nhập tương ứng, PasswordEncoder thực hiên mã hóa mật khẩu gửi lên và so sánh với mật khẩu đã được mã hóa lưu trong User đó. 

Điều này giúp bảo vệ mật khẩu của người dùng khỏi các cuộc tấn công bằng cách mã hóa mật khẩu và lưu trữ chúng trong cơ sở dữ liệu.

Thuộc tính của PasswordEncoder:
- encode(CharSequence rawPassword): Phương thức sử dụng để mã hóa mật khẩu.
- matches(CharSequence rawPassword, String encodedPassword): Phương thức so sánh mật khẩu đã mã hóa với mật khẩu đầu vào.

UserDetailsService là 1 interface triển khai 1 phương thức duy nhất để tìm kiếm thông tin người dùng trên database(được gọi bởi DaoAuthenticationProvider) qua tên đăng nhập(username): 
```java
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
```
- Tìm kiếm thành công trả về 1 Object UserDetails.
- Tìm kiếm thất bại ném 1 ngoại lệ UsernameNotFoundException (không tồn tại username...).

UserDetails là 1 interface  được sử dụng để lưu trữ thông tin xác thực của người dùng, chẳng hạn như tên đăng nhập, mật khẩu đã được mã hóa và danh sách các quyền được phân quyền.
```java
public interface UserDetails extends Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
```
các thuộc tính của UserDetails
- getUsername(): Phương thức này trả về tên đăng nhập.
- getPassword(): Phương thức này trả về mật khẩu đã được mã hóa.
- getAuthorities(): Phương thức này trả về danh sách các quyền được phân quyền.

Ngoài ra UserDetails có thể được tùy chỉnh (customize) bổ sung thêm các thuộc tính khác như địa chỉ email, số điện thoại, tên, v.v.

## Câu 2
Dựa vào hiểu biết của em. Hãy trình bày sơ lược về workflow trong Spring Security
- Người dùng gửi request thông tin tên, mật khẩu(username, password) => thông qua SecurityFilterChain tạo ra Object UserNamePasswordAuthenticationToken để lưu trữ thông tin.
- UserNamePasswordAuthenticationToken => chuyển vào AuthenticationManager => tìm tới AuthenticationProvider tương ứng
- AuthenticationProvider sử dụng UserDetailsService lấy ra các thông tin liên quan đến User (có tên = tên (username) được gửi lên) trong database
- Các thông tin của User được build thành 1 mẫu Object UserService (có thể tùy chỉnh thêm các thông tin khác ngoài tên, mật khấu lấy được từ database). 
  - Nếu không tồn tại User thì ném ra ngoại lệ UsernameNotFoundException 
- Sử dụng PasswordEncoder để mã hóa mật khẩu gửi lên (UserNamePasswordAuthenticationToken) và so sánh với thông tin mật khẩu đã được mã hóa trong UserDetail lấy từ database.
- Trường hợp mật khẩu phù hợp thì trả về 1 Object Authentication đã xác thực (chửa Object UserDetail kem theo các thông tin tùy chỉnh) lưu vào SecurityContextHolder. 
  - Xác thực không thành công trả về lỗi 401
- Tiếp tục kiểm tra Authentication có quyền thực hiện request không qua Authorities - mảng các quyền. 
  - Không có quyền thì báo lỗi 403.
## Câu 3
Session là gì? Cookie là gì? Nêu sự khác biệt giữa session và cookie
cái này chưa học thì phải?

Trả lời:

Session - phiên làm việc:
Khi người dùng truy cập vào trang web, một session mới được tạo ra và một session ID sẽ được gửi đến trình duyệt của người dùng. Session ID này sẽ được sử dụng để xác định phiên làm việc của người dùng trên server. Thông tin được lưu trữ trong session sẽ được duy trì cho đến khi phiên làm việc của người dùng kết thúc hoặc được hủy bởi server.

Cookie cũng được dùng để lưu những thông tin tạm thời. Nhưng tập tin cookie sẽ được truyền từ server tới trình duyệt và được lưu trữ trên máy tính của bạn khi bạn truy cập vào ứng dụng. Thông tin được lưu trữ trong cookie sẽ được duy trì cho đến khi thời gian hết hạn hoặc được xóa bởi người dùng.

So sánh sự khác biệt:
- Session lưu trữ và duy trì thông tin trên server <> Cookie lưu trữ và duy trì thông tin trên trình duyệt của người dùng.
- Session không có thời gian hết hạn cụ thể <> Cookie có thời gian hết hạn cụ thể.
- Session an toàn hơn Cookie vì thông tin được lưu trữ trên server, trong khi Cookie có thể bị đánh cắp hoặc giả mạo. Do đó Session được sử dụng để lưu trữ các thông tin nhạy cảm (đăng nhập, lịch sử truy cập...) <> Cookie được sử dụng để lưu trữ các thông tin không nhạy cảm(ngôn ngữ, hiển thị, vị trí...). 