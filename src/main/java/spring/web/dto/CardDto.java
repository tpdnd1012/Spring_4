package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.domain.card.CardEntity;

@Getter
@Setter
@NoArgsConstructor
public class CardDto {

    private Long id; // 카드번호
    private String cardname; // 카드명
    private String cardcompany; // 카드사
    private String cardlink; // 링크
    private String cardpicture; // 이미지
    private int cardcount; // 조회수

    @Builder
    public CardDto(Long id, String cardname, String cardcompany, String cardlink, String cardpicture, int cardcount) {
        this.id = id;
        this.cardname = cardname;
        this.cardcompany = cardcompany;
        this.cardlink = cardlink;
        this.cardpicture = cardpicture;
        this.cardcount = cardcount;
    }

    // dto --> entitiy 이동
    public CardEntity toEntity() {

        return CardEntity.builder()
                .id(id)
                .cardname(cardname)
                .cardcompany(cardcompany)
                .cardlink(cardlink)
                .cardpicture(cardpicture)
                .cardcount(cardcount).build();

    }

}
