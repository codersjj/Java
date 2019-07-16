import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// 业务类
public class MemberDao {
	Scanner input = new Scanner(System.in);
	// 不知道会员个数，用集合比数组好。这里的集合中只有会员，用泛型。ArrayList<>遍历更高效
	List<Member> memberList = new ArrayList<Member>();

	// 开始菜单
	public void menu() {
		System.out.println("*************************欢迎进入超市会员管理系统*************************");
		System.out.println("1.积分累计       2.积分兑换       3.查询剩余积分       4.修改密码       5.开卡       6.退出");
		System.out.println("*******************************************************************");
		System.out.print("请选择：");
	}

	public void start() {
		do {
			menu();
			int choice = input.nextInt();
			switch (choice) {
			case 1:
				if (saveScore()) {
					System.out.println("积分累计成功！");
				} else {
					System.out.println("积分累计失败！");
				}
				continue;
			case 2:
				if (minusScore()) {
					System.out.println("积分兑换成功！");
				} else {
					System.out.println("积分兑换失败！");
				}
				continue;
			case 3:
				showScore();
				continue;
			case 4:
				if (changePwd()) {
					System.out.println("修改密码成功！");
				} else {
					System.out.println("修改密码失败！");
				}
				continue;
			case 5:
				register();
				continue;
			case 6:
				System.out.println("感谢您的使用，欢迎下次使用！");
				break;
			default:
				System.out.println("您的操作有误，请重新选择：");
				continue;
			}
			break;
		} while (true);
	}

	// 查询当前会员是否存在，根据会员卡号及密码查找该会员是否存在，若存在，返回会员对象；不存在，返回null。
	public Member hasMember(int cardId, String pwd) {
		for (Member member : memberList) {
			if (member.getCardId() == cardId && member.getPassword().equals(pwd)) {
				return member;
			} 
		}
		return null;
	}
	
	// 积分累计
	public boolean saveScore() {
		boolean flag;
		System.out.print("请输入会员卡号：");
		int cardId = input.nextInt();
		System.out.print("请输入密码：");
		String pwd = input.next();
		Member member = hasMember(cardId, pwd);
		if (member != null) {
			System.out.print("请输入您此次的消费金额（1元积1分）：");
			int score = input.nextInt();
			member.setScore(member.getScore() + score);
			flag = true;
		} else {
			System.out.println("抱歉！该会员卡不存在，无法积分！");
			flag = false;
		}
		return flag;
	}

	// 积分兑换
	public boolean minusScore() {
		boolean flag;
		System.out.print("请输入会员卡号：");
		int cardId = input.nextInt();
		System.out.print("请输入密码：");
		String pwd = input.next();
		Member member = hasMember(cardId, pwd);
		if (member != null) {
			System.out.print("请输入您此次消费想兑换的积分：");
			int score = input.nextInt();
			if (score <= member.getScore()) {
				member.setScore(member.getScore() - score);
				System.out.println("您此次兑换了" + score / 100 * 0.1 + "元");
			} else {
				System.out.println("抱歉！积分不足，无法兑换！");
			}
			flag = true;
		} else {
			System.out.println("抱歉！该会员卡不存在，无法兑换积分！");
			flag = false;
		}
		return flag;
	}

	// 查询剩余积分
	public void showScore() {
		System.out.print("请输入会员卡号：");
		int cardId = input.nextInt();
		System.out.print("请输入密码：");
		String pwd = input.next();
		Member member = hasMember(cardId, pwd);
		if (member != null) {
			System.out.println("会员卡号\t姓名\t剩余积分\t开卡日期");
			System.out.println(member.getCardId() + "\t" + member.getName() + "\t" + member.getScore() + "\t" + member.getRegisterDate());
		} else {
			System.out.println("抱歉！该会员卡不存在，无法查询！");
		}

	}

	// 修改密码
	public boolean changePwd() {
		// 待完善
		return false;
	}

	// 生成随机卡号
	public int createId(){
		Random random = new Random();
		int cardId = random.nextInt(9999999);
		for (Member member : memberList) {
			// 若卡号已存在，则重新生成。
			if (member.getCardId() == cardId) {
				cardId = random.nextInt(9999999);
			}
		}
		
		return cardId;
	}
	
	// 开卡
	public void register() {
		Member member = new Member();
		System.out.print("请输入注册姓名：");
		member.setName(input.next());
		// 会员卡号随机生成
		int cardId = createId();
		member.setCardId(cardId);
		// 保证用户密码输入正确
		System.out.print("请输入注册密码：");
		String pwd;
		boolean flag = true;
		do{
			pwd = input.next();
			if (pwd.length() < 6) {
				System.out.print("会员密码不能小于6位！请重新输入您的密码：");
				flag = false;
			} else {
				flag = true;
				member.setPassword(pwd);
			}
		}while(!flag);
		// 每卡默认开卡后赠送100积分
		member.setScore(100);
		// 开卡日期
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String sDate = formater.format(date);
		member.setRegisterDate(sDate);
		System.out.println("恭喜您，会员卡开通成功！系统已赠送您100积分，您的会员卡号为：" + cardId);
		// 把会员加到会员集合中
		memberList.add(member);
	}

}
