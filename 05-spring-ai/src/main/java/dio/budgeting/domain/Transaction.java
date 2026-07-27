package dio.budgeting.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {
    private TransactionId id;
    private String description;
    private long amount;
    private Category category;

    public Transaction(String description, long amount, Category category) {
        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.category = category;
        
        // Valida o limite no momento da criação
        validateLimit();
    }
    
    public void validateLimit() {
        // Limite diário de 5.000,00 em centavos (500000)
        long maxLimit = 500000L; 
        
        if (this.amount > maxLimit) {
            throw new BusinessRuleException("O valor da transação ultrapassa o limite permitido de R$ 5.000,00.");
        }
    }
}
