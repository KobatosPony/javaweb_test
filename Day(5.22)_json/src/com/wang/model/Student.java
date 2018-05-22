package com.wang.model;

public class Student {
	private Integer id;
	private String name;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Student(Integer id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [id:"+id+"name:"+name+"address:"+address+"]";
	}
	
	// 返回json格式
	public String toJson() {
		return "{"+"\"id\":"+getJsonString(this.getId())
		+",\"name\":"+getJsonString(this.getName())
		+",\"address\":"+getJsonString(this.getAddress())+"}";
	}
	
	public String getJsonString(Object obj) {
		if(obj.getClass().getTypeName().equals("java.lang.String")) {
			return "\""+obj+"\"";
		}else {
			return ""+obj;
		}
	}
}
