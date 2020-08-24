package com.my.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Component(value="customer")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode(of= {"id"},callSuper =false)
public class Customer 
              extends Person {	
	private static final long serialVersionUID = 1L;
	@NonNull
	private String id;
	transient private String pwd;
	
//	public Customer() {
//		super();
//	}
//	public Customer(String id, String pwd) {
//		super();
//		this.id = id;
//		this.pwd = pwd;
//	}
//	public Customer(String id, String pwd, String name, String addr) {
//		super(name, addr);
//		
//		this.id = id;
//		this.pwd = pwd;
//	}
//	
//	public String getId() {
//		return id;
//	}
//	
//
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getPwd() {
//		return pwd;
//	}
//	public void setPwd(String pwd) {
//		this.pwd = pwd;
//	}
//	@Override
//	public String toString() {
//		return "id="+id+", pwd="+pwd+", name=" + name+ ", add=" + getAddr();
//	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Customer other = (Customer) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//	
	
	
}
