package com.atguigu.shiro.spring;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm{

	//进行授权的方法
	/**
	 * @param principals: 可以获取用户的登录信息， 即可以获取 doGetAuthenticationInfo 方法的返回值的 principal
	 * @return AuthorizationInfo: 通常使用SimpleAuthorizationInfo 作为返回值的实现类
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object principal = principals.getPrimaryPrincipal();
		
		System.out.println("通过 principal：" + principal + " 来获取当前用户所具有的权限...");
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("user");
		if("admin".equals(principal)){
			info.addRole("admin");
		}
		
		return info;
	}

	//进行认证的方法
	/**
	 * @return : 主要使用 SimpleAuthenticationInfo 作为实现类
	 * @param token : 即为 Handler 中调用 Subject 的 login 方法传入的 UsernamePasswordToken
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken aToken) throws AuthenticationException {
		
		//1. 强制类型转换
		UsernamePasswordToken token = (UsernamePasswordToken)aToken;
		//2. 获取用户名
		String username = token.getUsername();
		
		//3. 利用用户名从数据库中获取用户信息
		System.out.println("利用用户名: " + username + "获取用户信息。");
		
		//4. 返回 AuthenticationInfo 实例
		//principal: 登录信息 ，也可以是对像类型
		Object principal = username;
		//hashedCredentials: 凭证， 即第 3 步从数据库中获取到的用户的密码
		Object hashedCredentials = "8f80d39b51ca15a1f649e0665416e6ec";
		//credentialsSalt: 密码加密时的盐， 为 ByteSource 类型
		ByteSource credentialsSalt = ByteSource.Util.bytes("阿喽哈");
		//realmName: 当前 Realm 的 name, 可直接调用 getName() 方法完成
		String realmName = getName();
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		
		return info;
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		ByteSource salt = ByteSource.Util.bytes("阿喽哈");
		int hashIterations = 1024;
		
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}
