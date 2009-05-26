/**
 * 
 */
package com.pdpsoft.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.NoWorkFoundException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.core.io.FileSystemResource;

/**
 * @author shayan
 *
 */
public class BankFlatFileItemReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FlatFileItemReader itemReader = new FlatFileItemReader();
		itemReader.setResource(new FileSystemResource("C:/Java/development/spring-batch-sample/src/main/resources/KSH871205EL701.062.txt"));
		itemReader.setFieldSetMapper(new BankFieldSetMapper());
		
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setNames(new String[] {
				"BranchCode",
				"ChannelType",
				"PaymentDate",
				"BillId",
				"PaymentId",
				"ReferenceCode"
		});
		tokenizer.setColumns(new Range[] {
				new Range(1, 6), // BranchCode
				new Range(7, 8), // ChannelType
				new Range(9, 14), // Payment Date
				new Range(15, 27), // Bill Id
				new Range(28, 40), // Payment Id
				new Range(41, 46) // Reference Code
		});
		
		itemReader.setLineTokenizer(tokenizer);
		//itemReader.setFirstLineIsHeader(true);
		itemReader.setLinesToSkip(1);
		itemReader.open(new ExecutionContext());
		
		List<BankEntity> entities = new ArrayList<BankEntity>();
		try {
			BankEntity entity = (BankEntity) itemReader.read();
			if(entity == null) {
				System.out.println("Within if and entity is null !!!");
				return;
			}
			while(entity != null) {
				entities.add(entity);

/*				System.out.println(entity.getBranchCode());
				System.out.println(entity.getBranchCode().length());

				System.out.println(entity.getChannelType());
				System.out.println(entity.getChannelType().length());

				System.out.println(entity.getPaymentDate());
				System.out.println(entity.getPaymentDate().length());

				System.out.println(entity.getBillId());
				System.out.println(entity.getBillId().length());

				System.out.println(entity.getPaymentId());
				System.out.println(entity.getPaymentId().length());

				System.out.println(entity.getReferenceCode());
				System.out.println(entity.getReferenceCode().length());
*/
				entity = (BankEntity) itemReader.read();				
			}
			System.out.println(entities.size());
		} catch (UnexpectedInputException e) {
			e.printStackTrace();
		} catch (NoWorkFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
