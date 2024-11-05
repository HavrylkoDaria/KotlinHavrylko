import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val switch: Switch = itemView.findViewById(R.id.switchView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        // Встановлюємо текст та колір на основі стану
        holder.textView.text = item.text
        holder.textView.setBackgroundColor(
            if (item.isChecked) Color.GREEN else Color.RED
        )

        // Встановлюємо стан Switch
        holder.switch.isChecked = item.isChecked

        // Додаємо слухач для зміни стану Switch
        holder.switch.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            item.text = if (isChecked) "ON" else "OFF"
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = items.size
}
