package intern.demo.domain.user.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class UserCsvDto {

    @Setter
    private String id;

    @CsvBindByName(column = "[shorts] agreeLicensedVideo")
    private String shortsAgreeLicensedVideo;

    @CsvBindByName(column = "[shorts] certified")
    private String shortsCertified;

    @CsvBindByName(column = "[Shorts] Commission")
    private Integer shortsCommission;

    @CsvBindByName(column = "access_token")
    private String accessToken;

    private String admin;

    private String agreementPrivacy;

    private String agreementTemrs;

    private String alarmYn;

    private String bankAccountHolder;

    private String bankAccountNumber;

    private String bankName;

    private String bio;

    private String blocked;

    private String businessAccount;

    private String businessBankAccountFile;

    @CsvBindByName(column = "businessReg-Docu")
    private String businessRegDocu;

    private String dateOfBirth;

    private String deviceOS;

    private String firstSubmittion;

    private List followers;

    private List following;

    private String gcmKey;

    private String identificationNumber;

    private String imgProfile;

    private String joinedAt;

    private String labelName;

    private String laeeblyEmail;

    private String laeeblyId;

    private String laeeblyMemo;

    private Integer laeeblyPurchasedAmount;

    private Integer laeeblyPurchasedCount;

    private String language;

    private String leftAt;

    private String leftReason;

    private Integer memberIdx;

    private String membership;

    @CsvBindByName(column = "membershipPaid (deleted)")
    private List membershipPaid;

    private String membershipStatus;

    private String name;

    @CsvBindByName(column = "NFTwallet")
    private String nftWallet;

    private String nickName;

    @CsvBindByName(column = "Partner")
    private String partner;

    private String payerID;

    private String phoneNumber;

    private List referrer;

    @CsvBindByName(column = "refresh_token")
    private String refreshToken;

    private List role;

    @Setter
    @CsvBindByName(column = "Creation Date")
    private String created_date;

    @Setter
    @CsvBindByName(column = "Modified Date")
    private String modified_date;

    private String email;

    @CsvBindByName(column = "null")
    private String nullColumn;

    @CsvBindByName(column = "unique id")
    private String uniqueID;
}