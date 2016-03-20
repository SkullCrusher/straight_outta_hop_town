package package_1;

import java.util.LinkedList;
import java.util.List;

import gov.nasa.worldwind.geom.Position;

public class Alert {
	private List<Position> position;
	private String linkUrl;
	private String summary;
	private String event;
	private String effTime;
	private String expTime;
	private String stat;
	private String msgType;
	private String category;
	private String urgency;
	private String severity;
	private String certainty;
	private String areaDesc;
	
	public Alert(){
		this.position = new LinkedList<Position>();
	}
	public String getAreaDesc() {
		return areaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	public String getCertainty() {
		return certainty;
	}
	public void setCertainty(String certainty) {
		this.certainty = certainty;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	public String getEffTime() {
		return effTime;
	}
	public void setEffTime(String effTime) {
		this.effTime = effTime;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Position popPosition() {
		if(this.position.isEmpty()){
			return null;
		}
		return this.position.remove(0);
	}
	public Position getPosition(int index){
		return this.position.get(index);
	}
	public void pushPosition(Position position){
		this.position.add(position);
	}
	public int getPosCount(){
		return this.position.size();
	}
	
}
