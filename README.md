# JavaPractice

## FINAL 변수
지역변수 나 필드를 final로 정의하기 때문에 이 변수에 값을 재할당할 수 없다.
코드에 가능한 많은 변수를 final로 표시하면 어떤 객체의 상태값이 바뀔 수 있고, 어떤 객체의 상태가 바뀔 수 없는지 명확하게 구분할 수 있다.
final 필드로 가리키는 객체라도 가변 상태를 포함하기 때문에 객체가 바뀌지 못하도록 강요하는 것은 아님.
추상메서드(인터페이스)의 파라미터에 사용 시 실제 구현이 없으므로 키워드의 의미가 무력화 되며 자바10에서 var 키워드 등장하면서 유용성이 크게 감소됨

## 코드유지보수성과 안티 패턴
1.코드 유지보수성
- 특정 기능을 담당하는 코드를 쉽게 찾을 수 있어야 한다.
- 코드가 어떤 일을 수행하는 지 쉽게 이해할 수 있어야 한다.
- 새로운 기능을 쉽게 추가하거나 기존 기능을 쉽게 제거할 수 있어야 한다.
- 캡슐화가 잘되어 있어야 한다. 즉 코드 사용자에게는 세부 구현 내용이 감춰져 있으므로 사용자가 쉽게 코드를 이해하고, 기능을 바꿀 수 있어야 한다.

2. 안티 패턴
- 한개의 거대한 갓 클래스(God Class) 때문에 코드를 이해하기 어렵다.
- 코드중복 때문에 코드가 불안정하고 변화에 쉽게 망가진다.
  * 갓클래스 : 한개의 파일에 모든 코드를 구현. 클래스의 목적 / 로직을 파악하기 어려워지며 수정하기 어려움. 이를 갓 클래스 안티패턴이라고 부름.

## 단일 책임 원칙
단일 책임 원칙(SRP) 쉽게 관리하고 유지보수하는 코드를 구현하는 데 도움을 주는 포괄적인 소프트웨어 개발 지침
- 한 클래스는 한 기능만 책임진다.
- 클래스가 바뀌어야 하는 이유는 오직 하나여야 한다.

## 응집도
응집도는 서로 어떻게 관련되어 있는지를 가리킨다. 정확히 말하자면 응지볻는 클래스나 메서드의 책임이 서로 얼마나 강하게 연결되어 있는지를 측정한다.

1. 클래스 수준 응집도
  - 기능
  - 정보
  - 유틸리티
  - 논리
  - 순차
  - 시간
  그룹화하는 메서드의 관련성이 약하면 응집도가 낮아진다.
  
#### 기능 
기능이 비슷한 메서드를 그룹화
함께 사용하는 메서드를 그룹화하면 찾기도 쉽고 이해하기 쉬우므로 응집도를 높인다. 다만 기능 응집은 한 개의 메서드를 갖는 클래스를 너무 과도하게 만들려는 경향이 발생할 수 있다.
간단한 클래스를 과도하게 만들면 그만큼 생각해야 할 클래스가 많아지므로 코드가 장황해지고 복잡해진다.

#### 정보
같은 데이터나 도메인 객체를 처리하는 메서드를 그룹화
정보 응집은 여러 기능을 그룹화하면서, 필요한 일부 기능을 포함하는 클래스 전체를 디펜던시로 추가한다는 약점이 있다.

#### 유틸리티
때로는 관련성이 없는 메서드를 한 클래스로 포함시켜야 한다.
유틸리티 클래스의 사용은 낮은 응집도로 이어지므로 자제 해야한다. 메서드가 서로 연관성이 없으므로 클래스 전체의 기능을 추론하기 어려움.

#### 논리
```Java
public class BankStatementFullParser {

    BankTransaction parseFromCSV(final String line) {
        // ...
        throw new UnsupportedOperationException();
    }

    BankTransaction parseFromJSON(final String line) {
        // ...
        throw new UnsupportedOperationException();
    }

    BankTransaction parseFromXML(final String line) {
        // ...
        throw new UnsupportedOperationException();
    }
}
```
CSV,Json,xml 의 자료를 파싱하는 코드를 구현한다는 가정
예제에서 네 개의 메서드는 파싱이라는 논리로 그룹화. 이렇게 그룹화 할 시 클래스는 4가지 책임을 갖게 되어, SRP를 위배함. 
결과적으로 이방법을 권장하지 않음.

#### 순차
입출력이 순차적으로 흐르는 것을 순차 응집이라고 함.
순차 응집은 여러 동작이 어떻게 함께 수행되는 지 쉽게 이해할 수 있다. 하지만 적용 시 한 클래스를 바꿔야 할 여러 이유가 존재하게 되어 SRP를 위배함.

#### 시간
시간 응집 클래스는 시간과 관련된 연산을 그룹화 함.

<실전자바소프트웨어개발 p.38>

![image](https://user-images.githubusercontent.com/26279988/128433943-9f71eda0-0d2d-4d88-8f81-6c4bc6ce20a1.png)

#### 메서드 수준 응집도

메서드가 다양한 기능을 수행할 수록 메서드가 어떤 동작을 처리하는 지 이해하기가 점점 어려워진다.
일반적으로 클래스나 메서드 파라미터의 여러 필드를 바꾸는 if/else 블록이 여러개 포함되어 있다면, 이는 응집도에 문제가 있음.
응집도가 높은 더 작은 조각으로 메서드를 분리해야함.


## 결합도
결합도는 한 기능이 다른 클래스에 얼마나 의존하고 있는 지.
어떤 클래스를 구현하는 데 얼마나 많은 클래스를 참조했는 가로 설명할 수 있음.

<실전자바소프트웨어개발 p.41>

![image](https://user-images.githubusercontent.com/26279988/128436946-1d784ead-168e-438c-9e79-d175cb9a8b0d.png)

보통 코드를 구현할 때 결합도를 낮춰야 한다. 이는 코드의 다양한 컴포넌트가 내부와 세부 구현에 의존하지 않아야 함을 의미한다.

## 테스트
Junit(제이유닛)을 통해 테스트를 구현
테스트 장점
  - 확신
  - 변화에도 튼튼함을 유지
  - 프로그램 이해도
  
Maven, gradle 빌드 도구에서는 src/main/java에 코드를 저장하고, src/test/java에 테스트 클래스를 저장하는 것이 기본규칙


```Java
	@Test
	public void shouldParseOneCorrectLine() throws Exception {
		//Assert.fail("Not yet implemented");
		
		final String line = "30-01-2017, -50,Tesco";
		
		final BankTransaction result = statementParser.parseFrom(line);
		final BankTransaction expected = new BankTransaction(LocalDate.of(2017,Month.JANUARY, 30), -50, "Tesco");
		
		final double tolerance = 0.0d;
		
		Assert.assertEquals(expected.getDate(), result.getDate());
		Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
		Assert.assertEquals(expected.getDescription(), result.getDescription());
	}
```

1. 테스트의 콘텍스트를 설정
2. 동작을 실행
3. 예상된 결과를 어서션으로 지정하고 확인
유닛 테스트 설정의 세 단계 패턴을 Given-When-Then 공식이라 부른다.

어서션 구문 (실전 자바 소프트웨어 개발 p.46 표 2-2)

![image](https://user-images.githubusercontent.com/26279988/128439165-162390f2-346b-499d-9064-d75f6e90ca5d.png)


### 개방/폐쇄 원칙

함수형 인터페이스(@FunctionalInterface) : 한 개의 추상메서드를 포함하는 인터페이스 



