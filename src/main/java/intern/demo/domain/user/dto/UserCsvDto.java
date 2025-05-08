package intern.demo.domain.user.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserCsvDto {

    @CsvBindByName(column = "[shorts] agreeLicensedVideo")
    private String shortsAgreeLicensedVideo;

    @CsvBindByName(column = "[shorts] certified")
    private String shortsCertified;

    @CsvBindByName(column = "[Shorts] Commission")
    private Integer shortsCommission;

    @CsvBindByName(column = "access_token")
    private String accessToken;

    @CsvBindByName(column = "admin")
    private String admin;

    @CsvBindByName(column = "agreementPrivacy")
    private String agreementPrivacy;

    @CsvBindByName(column = "agreementTemrs")
    private String agreementTemrs;

    @CsvBindByName(column = "alarmYn")
    private String alarmYn;

    @CsvBindByName(column = "bankAccountHolder")
    private String bankAccountHolder;

    @CsvBindByName(column = "bankAccountNumber")
    private String bankAccountNumber;

    @CsvBindByName(column = "bankName")
    private String bankName;

    @CsvBindByName(column = "bio")
    private String bio;

    @CsvBindByName(column = "blocked")
    private String blocked;

    @CsvBindByName(column = "businessAccount")
    private String businessAccount;

    @CsvBindByName(column = "businessBankAccountFile")
    private String businessBankAccountFile;

    @CsvBindByName(column = "businessReg-Docu")
    private String businessRegDocu;

    @CsvBindByName(column = "dateOfBirth")
    private String dateOfBirth;

    @CsvBindByName(column = "deviceOS")
    private String deviceOS;

    @CsvBindByName(column = "firstSubmittion")
    private String firstSubmittion;

    @CsvBindByName(column = "followers")
    private String followers;

    @CsvBindByName(column = "following")
    private String following;

    @CsvBindByName(column = "gcmKey")
    private String gcmKey;

    @CsvBindByName(column = "identificationNumber")
    private String identificationNumber;

    @CsvBindByName(column = "imgProfile")
    private String imgProfile;

    @CsvBindByName(column = "joinedAt")
    private String joinedAt;

    @CsvBindByName(column = "labelName")
    private String labelName;

    @CsvBindByName(column = "laeeblyEmail")
    private String laeeblyEmail;

    @CsvBindByName(column = "laeeblyId")
    private String laeeblyId;

    @CsvBindByName(column = "laeeblyMemo")
    private String laeeblyMemo;

    @CsvBindByName(column = "laeeblyPurchasedAmount")
    private Integer laeeblyPurchasedAmount;

    @CsvBindByName(column = "laeeblyPurchasedCount")
    private Integer laeeblyPurchasedCount;

    @CsvBindByName(column = "language")
    private String language;

    @CsvBindByName(column = "leftAt")
    private String leftAt;

    @CsvBindByName(column = "leftReason")
    private String leftReason;

    @CsvBindByName(column = "memberIdx")
    private Integer memberIdx;

    @CsvBindByName(column = "membership")
    private String membership;

    @CsvBindByName(column = "membershipPaid (deleted)")
    private String membershipPaid;

    @CsvBindByName(column = "membershipStatus")
    private String membershipStatus;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "NFTwallet")
    private String nftWallet;

    @CsvBindByName(column = "nickName")
    private String nickName;

    @CsvBindByName(column = "Partner")
    private String partner;

    @CsvBindByName(column = "payerID")
    private String payerID;

    @CsvBindByName(column = "phoneNumber")
    private String phoneNumber;

    @CsvBindByName(column = "referrer")
    private String referrer;

    @CsvBindByName(column = "refresh_token")
    private String refreshToken;

    @CsvBindByName(column = "role")
    private String role;

    @Setter
    @CsvBindByName(column = "Creation Date")
    private String created_date;

    @Setter
    @CsvBindByName(column = "Modified Date")
    private String modified_date;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "null")
    private String nullColumn;

    @CsvBindByName(column = "unique id")
    private String uniqueID;

    @Setter
    private String id;
}