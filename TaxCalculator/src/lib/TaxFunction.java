package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            throw new IllegalArgumentException("Invalid number of months working per year");
        }

        int taxDeductible = calculateTaxDeductible(isMarried, numberOfChildren);

        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - taxDeductible;

        int tax = (int) Math.round(0.05 * taxableIncome);

        return Math.max(tax, 0); // Menghindari pajak bernilai dibawah 0
    }

	private static int calculateTaxDeductible(boolean isMarried, int numberOfChildren) {
        int taxDeductible = 54000000; // Pajak untuk yang belum menikah
        if (isMarried) {
            taxDeductible += 4500000; // Pajak untuk yang sudah menikah
        }
        taxDeductible += calculateChildDeductible(numberOfChildren);
        return taxDeductible;
    }

	private static int calculateChildDeductible(int numberOfChildren) {
        int maxChildrenDeductible = 3 * 1500000; // Jumlah maksimum pajak untuk keluarga beranak 3
        return Math.min(numberOfChildren * 1500000, maxChildrenDeductible);
    }
	
}
